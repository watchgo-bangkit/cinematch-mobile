package com.example.cinematch.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinematch.api.ApiService
import com.example.cinematch.api.RetrofitClient
import com.example.cinematch.api.TokenManager
import com.example.cinematch.data.LoginRequest
import com.example.cinematch.data.RegisterResponse
import com.example.cinematch.data.WatchlistResponse
import com.example.cinematch.data.WatchlistUpdateResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WatchlistViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application)
    val watchlist = MutableLiveData<List<WatchlistResponse>>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()
    val watchedMovies = MutableLiveData<List<WatchlistResponse>>()

    init {
        RetrofitClient.initialize(tokenManager)
    }

    fun fetchWatchlist() {
        loading.value = true
        viewModelScope.launch {
            try {
                val watchlistData = RetrofitClient.apiService.getWatchlist()
                watchlist.postValue(watchlistData)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.postValue(false)
            }
        }
    }

    fun fetchWatched() {
        loading.value = true
        viewModelScope.launch {
            try {
                val watchlistData = RetrofitClient.apiService.getWatchlist()
                watchedMovies.postValue(watchlistData.filter { it.is_watched })
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.postValue(false)
            }
        }
    }

    fun updateWatchlist(id: Int) {
        viewModelScope.launch {
            loading.value = true
            RetrofitClient.apiService.updateWatchlist(id).enqueue(object :
                Callback<WatchlistUpdateResponse> {
                override fun onResponse(
                    call: Call<WatchlistUpdateResponse>,
                    response: Response<WatchlistUpdateResponse>
                ) {
                    if (response.isSuccessful) {
                        fetchWatchlist()
                        errorMessage.postValue("Successfully added to watched")
                    } else {
                        errorMessage.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<WatchlistUpdateResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

    fun clearErrorMessage() {
        errorMessage.value = ""
    }
}
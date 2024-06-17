package com.example.cinematch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinematch.api.RetrofitClient
import com.example.cinematch.api.TokenManager
import com.example.cinematch.data.MovieDetailResponse
import kotlinx.coroutines.launch

class MovieDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val tokenManager = TokenManager(application)
    val movieDetail = MutableLiveData<MovieDetailResponse>()
    val loading = MutableLiveData<Boolean>()
    val errorMessage = MutableLiveData<String>()

    init {
        RetrofitClient.initialize(tokenManager)
    }

    fun fetchMovieDetail(movieId: Int) {
        loading.value = true
        viewModelScope.launch {
            try {
                val response = RetrofitClient.apiService.getMovieDetail(movieId)
                movieDetail.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
                errorMessage.postValue(e.message)
            } finally {
                loading.postValue(false)
            }
        }
    }
}
package com.example.cinematch.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinematch.api.RetrofitClient
import com.example.cinematch.data.GenreResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GenreViewModel(application: Application) : AndroidViewModel(application) {

    val genreResponse = MutableLiveData<GenreResponse>()
    val errorMessage = MutableLiveData<String>()

    fun fetchGenres() {
        viewModelScope.launch {
            RetrofitClient.apiService.getGenres().enqueue(object : Callback<GenreResponse> {
                override fun onResponse(call: Call<GenreResponse>, response: Response<GenreResponse>) {
                    if (response.isSuccessful) {
                        genreResponse.postValue(response.body())
                    } else {
                        errorMessage.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<GenreResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }
}
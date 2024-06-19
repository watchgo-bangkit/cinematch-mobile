package com.example.cinematch.viewmodel

import com.example.cinematch.data.RecommendationResponse
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cinematch.api.RetrofitClient
import com.example.cinematch.api.TokenManager
import kotlinx.coroutines.launch

class RecommendationViewModel(application: Application) : AndroidViewModel(application) {

    val recommendationResponse = MutableLiveData<List<RecommendationResponse>>()
    val loading = MutableLiveData<Boolean>()
    private val tokenManager = TokenManager(application)

    init {
        RetrofitClient.initialize(tokenManager)
    }

    fun fetchRecommendations() {
        loading.value = true
        viewModelScope.launch {
            try {
                val recommendationData = RetrofitClient.apiService.getRecommendations()
                recommendationResponse.postValue(recommendationData)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                loading.postValue(false)
            }
        }
    }
}
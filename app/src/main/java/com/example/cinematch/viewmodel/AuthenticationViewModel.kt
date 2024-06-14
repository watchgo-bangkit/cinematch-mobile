package com.example.cinematch.viewmodel

// AuthenticationViewModel.kt

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.cinematch.api.RetrofitClient
import com.example.cinematch.api.TokenManager
import com.example.cinematch.data.LoginRequest
import com.example.cinematch.data.LoginResponse
import com.example.cinematch.data.RegisterRequest
import com.example.cinematch.data.RegisterResponse
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthenticationViewModel(application: Application) : AndroidViewModel(application) {

    val registerResponse = MutableLiveData<RegisterResponse>()
    val loginResponse = MutableLiveData<LoginResponse>()
    val errorMessage = MutableLiveData<String>()
    private val tokenManager = TokenManager(application)

    init {
        RetrofitClient.initialize(tokenManager)
    }

    fun registerAndLogin(request: RegisterRequest, navController: NavController) {
        viewModelScope.launch {
            RetrofitClient.apiService.register(request).enqueue(object : Callback<RegisterResponse> {
                override fun onResponse(call: Call<RegisterResponse>, response: Response<RegisterResponse>) {
                    if (response.isSuccessful) {
                        registerResponse.postValue(response.body())
                        val loginRequest = LoginRequest(request.email, request.password)
                        login(loginRequest, navController)
                    } else {
                        Log.d("RegisterResponseFailed", "Received: $response")
                        errorMessage.postValue(response.message())
                    }
                }

                override fun onFailure(call: Call<RegisterResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

    fun login(request: LoginRequest, navController: NavController) {
        viewModelScope.launch {
            RetrofitClient.apiService.login(request).enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        val loginResp = response.body()
                        loginResponse.postValue(loginResp)
                        loginResp?.data?.token?.let {
                            tokenManager.saveToken(it)
                            navController.navigate("home") {
                                popUpTo("landing") { inclusive = true }
                            }
                        }
                    } else {
                        val errorBody = response.errorBody()?.string()
                        val errorMsg = extractErrorMessage(errorBody)
                        errorMessage.postValue(errorMsg)
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    errorMessage.postValue(t.message)
                }
            })
        }
    }

    private fun extractErrorMessage(errorBody: String?): String {
        return if (!errorBody.isNullOrEmpty()) {
            try {
                val type = object : TypeToken<Map<String, String>>() {}.type
                val errorMap: Map<String, String> = Gson().fromJson(errorBody, type)
                errorMap["error"] ?: "Unknown error"
            } catch (e: Exception) {
                "Unknown error"
            }
        } else {
            "Unknown error"
        }
    }

    fun logout() {
        tokenManager.clearToken()
    }
}


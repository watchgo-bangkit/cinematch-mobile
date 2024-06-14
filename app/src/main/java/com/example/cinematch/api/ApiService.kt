package com.example.cinematch.api

import com.example.cinematch.data.GenreResponse
import com.example.cinematch.data.LoginRequest
import com.example.cinematch.data.LoginResponse
import com.example.cinematch.data.RegisterRequest
import com.example.cinematch.data.RegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST(ApiConfig.REGISTER_ENDPOINT)
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST(ApiConfig.LOGIN_ENDPOINT)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(ApiConfig.GET_GENRE_ENDPOINT)
    fun getGenres(): Call<GenreResponse>
}
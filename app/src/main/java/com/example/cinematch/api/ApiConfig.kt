package com.example.cinematch.api

import com.example.cinematch.BuildConfig

object ApiConfig {
    const val BASE_URL = BuildConfig.API_BASE_URL // Replace with your actual base URL
    const val REGISTER_ENDPOINT = "/api/auth/register"
    const val LOGIN_ENDPOINT = "/api/auth/login"
    const val GET_GENRE_ENDPOINT = "/api/movies/genres"
}
package com.example.cinematch.api

import com.example.cinematch.BuildConfig

object ApiConfig {
    const val BASE_URL = BuildConfig.API_BASE_URL // Replace with your actual base URL
    const val REGISTER_ENDPOINT = "/api/auth/register"
    const val LOGIN_ENDPOINT = "/api/auth/login"
    const val GET_GENRE_ENDPOINT = "/api/movies/genres"
    const val GET_WATCHLIST_ENDPOINT = "/api/watchlist"
    const val GET_MOVIES_ENDPOINT = "/api/movies"
    const val GET_RECOMMENDATIONS_ENDPOINT = "/api/recommendations"
}
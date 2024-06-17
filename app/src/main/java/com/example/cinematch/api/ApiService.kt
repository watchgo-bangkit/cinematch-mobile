package com.example.cinematch.api

import com.example.cinematch.data.GenreResponse
import com.example.cinematch.data.LoginRequest
import com.example.cinematch.data.LoginResponse
import com.example.cinematch.data.MovieDetailResponse
import com.example.cinematch.data.RegisterRequest
import com.example.cinematch.data.RegisterResponse
import com.example.cinematch.data.WatchlistResponse
import com.example.cinematch.data.WatchlistUpdateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST(ApiConfig.REGISTER_ENDPOINT)
    fun register(@Body request: RegisterRequest): Call<RegisterResponse>

    @POST(ApiConfig.LOGIN_ENDPOINT)
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @GET(ApiConfig.GET_GENRE_ENDPOINT)
    fun getGenres(): Call<GenreResponse>

    @GET(ApiConfig.GET_WATCHLIST_ENDPOINT)
    suspend fun getWatchlist(): List<WatchlistResponse>

    @PATCH("${ApiConfig.GET_WATCHLIST_ENDPOINT}/{id}")
    fun updateWatchlist(
        @Path("id") id: Int,
    ): Call<WatchlistUpdateResponse>

    @GET("${ApiConfig.GET_MOVIES_ENDPOINT}/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponse
}
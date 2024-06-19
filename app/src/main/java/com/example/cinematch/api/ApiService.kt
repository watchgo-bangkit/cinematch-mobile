package com.example.cinematch.api

import com.example.cinematch.data.AddWatchlistResponse
import com.example.cinematch.data.GenreResponse
import com.example.cinematch.data.LoginRequest
import com.example.cinematch.data.LoginResponse
import com.example.cinematch.data.MovieDetailResponse
import com.example.cinematch.data.RecommendationResponse
import com.example.cinematch.data.RegisterRequest
import com.example.cinematch.data.RegisterResponse
import com.example.cinematch.data.WatchlistRequest
import com.example.cinematch.data.WatchlistResponse
import com.example.cinematch.data.WatchlistUpdateResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
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

    @POST(ApiConfig.GET_WATCHLIST_ENDPOINT)
    fun addWatchlist(@Body request: WatchlistRequest): Call<AddWatchlistResponse>

    @GET("${ApiConfig.GET_MOVIES_ENDPOINT}/{movieId}")
    suspend fun getMovieDetail(@Path("movieId") movieId: Int): MovieDetailResponse

    @GET(ApiConfig.GET_RECOMMENDATIONS_ENDPOINT)
    suspend fun getRecommendations(): List<RecommendationResponse>
}
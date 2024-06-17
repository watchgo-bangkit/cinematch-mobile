package com.example.cinematch.data

data class WatchlistResponse(
    val id: Int,
    val movie_id: Int,
    val title: String,
    val poster_path: String,
    val released_year: String,
    val runtime: Int,
    val vote_average: Double,
    val like: Boolean,
    val is_watched: Boolean
)
package com.example.cinematch.data

data class WatchlistRequest(
    val movie_id : Int,
    val liked : Boolean,
    val is_watched : Boolean,
)
package com.example.cinematch.data

data class AddWatchlistResponse(
    val id : Int,
    val user_id : Int,
    val movie_id : Int,
    val liked : Boolean,
    val is_watched : Boolean
)
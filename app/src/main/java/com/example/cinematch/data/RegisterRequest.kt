package com.example.cinematch.data

data class RegisterRequest(
    val name: String,
    val email: String,
    val password: String,
    val gender: String,
    val age: Int,
    val genre_preferences: List<Int>
)
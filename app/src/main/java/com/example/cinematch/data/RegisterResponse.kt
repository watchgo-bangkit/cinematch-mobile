package com.example.cinematch.data

data class RegisterResponse(
    val data: Data
) {
    data class Data(
        val id: Int,
        val name: String,
        val email: String,
        val password: String,
        val age: Int,
        val gender: String,
        val genre_preferences: List<Genre>
    )
}
package com.example.cinematch.data

data class LoginResponse(
    val data: Data
) {
    data class Data(
        val user: User,
        val token: String
    ) {
        data class User(
            val id: Int,
            val name: String,
            val email: String,
            val password: String,
            val age: Int,
            val gender: String
        )
    }
}
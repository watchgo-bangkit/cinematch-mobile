package com.example.cinematch.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.cinematch.data.LoginResponse
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TokenManager(context: Context) : TokenProvider {

    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val USER_PROFILE_KEY = "USER_PROFILE_KEY"
    }

    override fun getToken(): String? {
        val token = preferences.getString(TOKEN_KEY, null)
        return token
    }

    fun saveToken(token: String) {
        preferences.edit().putString(TOKEN_KEY, token).apply()
    }

    fun clearToken() {
        preferences.edit().remove(TOKEN_KEY).apply()
    }

    fun isTokenValid(): Boolean {
        val valid = getToken() != null
        return valid
    }

    fun saveUserProfile(userProfile: LoginResponse) {
        val json = gson.toJson(userProfile)
        preferences.edit().putString(USER_PROFILE_KEY, json).apply()
    }

    fun getUserProfile(): LoginResponse? {
        val json = preferences.getString(USER_PROFILE_KEY, null)
        return if (json != null) {
            gson.fromJson(json, LoginResponse::class.java)
        } else {
            null
        }
    }

    fun clearUserProfile() {
        preferences.edit().remove(USER_PROFILE_KEY).apply()
    }

    fun clearAll() {
        preferences.edit().clear().apply()
    }
}

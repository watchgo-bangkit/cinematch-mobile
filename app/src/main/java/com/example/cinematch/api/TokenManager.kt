package com.example.cinematch.api

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder

class TokenManager(context: Context) : TokenProvider {

    private val preferences: SharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE)
    private val gson: Gson = GsonBuilder().setPrettyPrinting().create()

    companion object {
        private const val TOKEN_KEY = "TOKEN_KEY"
        private const val TAG = "TokenManager"
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
}

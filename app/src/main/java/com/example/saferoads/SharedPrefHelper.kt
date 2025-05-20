package com.example.saferoads

import android.content.Context
import android.content.SharedPreferences

object SharedPrefHelper {

    private const val PREF_NAME = "UserPreferences"

    // Save user credentials
    fun saveUser(context: Context, username: String, email: String, phone: String, address: String, password: String) {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("username", username)
        editor.putString("email", email)
        editor.putString("phone", phone)
        editor.putString("address", address)
        editor.putString("password", password)
        editor.apply()
    }

    // Get user credentials
    fun getUser(context: Context): User {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val username = sharedPreferences.getString("username", "")
        val email = sharedPreferences.getString("email", "")
        val phone = sharedPreferences.getString("phone", "")
        val address = sharedPreferences.getString("address", "")
        val password = sharedPreferences.getString("password", "")
        return User(username ?: "", email ?: "", phone ?: "", address ?: "", password ?: "")
    }

    // Validate user credentials during login
    fun isValidUser(context: Context, username: String, password: String): Boolean {
        val sharedPreferences: SharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val storedUsername = sharedPreferences.getString("username", "")
        val storedPassword = sharedPreferences.getString("password", "")
        return storedUsername == username && storedPassword == password
    }
}

// User data class to store user info
data class User(val username: String, val email: String, val phone: String, val address: String, val password: String)

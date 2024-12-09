package ru.miel.data.storage

import android.content.SharedPreferences
import ru.miel.domain.istorage.ITokenStorage

class TokenStorage(
    private val sharedPreferences: SharedPreferences

) : ITokenStorage {
    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    override fun getToken(): String {
        val token = sharedPreferences.getString(TOKEN, "") ?: ""
        return token
    }

    companion object {
        private const val TOKEN = "token"
    }
}

package ru.miel.data.storage

import android.content.SharedPreferences
import ru.miel.domain.istorage.IUserStorage
import ru.miel.domain.models.InfoUser

class UserStorage(
    private val sharedPreferences: SharedPreferences

) : IUserStorage {

    override fun saveInfoUser(infoUser: InfoUser) {
        sharedPreferences.edit().putString(FULL_NAME, infoUser.full_name).apply()
        sharedPreferences.edit().putString(EMAIL, infoUser.email).apply()
        sharedPreferences.edit().putString(PHONE, infoUser.phone).apply()
        sharedPreferences.edit().putString(PHOTO, infoUser.photo).apply()
        sharedPreferences.edit().putString(OFFICE_NAME, infoUser.office_name).apply()
        sharedPreferences.edit().putString(OFFICE_LOCATION, infoUser.office_location).apply()
        sharedPreferences.edit().putString(DEPARTMENT, infoUser.department).apply()
    }

    override fun getInfoUser(): InfoUser {
        val infoUser = InfoUser(
            full_name = sharedPreferences.getString(FULL_NAME, "") ?: "",
            email = sharedPreferences.getString(EMAIL, "") ?: "",
            phone = sharedPreferences.getString(PHONE, ""),
            photo = sharedPreferences.getString(PHOTO, ""),
            office_name = sharedPreferences.getString(OFFICE_NAME, "") ?: "",
            office_location = sharedPreferences.getString(OFFICE_LOCATION, "") ?: "",
            department = sharedPreferences.getString(DEPARTMENT, "") ?: "",
        )
        return infoUser

    }

    override fun getFullNameInfoUser(): String? {
        return sharedPreferences.getString(FULL_NAME, "")
    }

    override fun getPhotoInfoUser(): String? {
        return sharedPreferences.getString(PHOTO, "")
    }

    override fun getOfficeNameInfoUser(): String? {
        return sharedPreferences.getString(OFFICE_NAME, "")
    }

    companion object {
        private const val FULL_NAME = "full_name"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
        private const val PHOTO = "photo"
        private const val OFFICE_NAME = "office_name"
        private const val OFFICE_LOCATION = "office_location"
        private const val DEPARTMENT = "department"
    }
}

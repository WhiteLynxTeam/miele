package ru.miel.domain.istorage

import ru.miel.domain.models.InfoUser

interface IUserStorage {
    fun saveInfoUser(infoUser: InfoUser)
    fun getInfoUser(): InfoUser
    fun getFullNameInfoUser(): String?
    fun getPhotoInfoUser(): String?
    fun getOfficeNameInfoUser(): String?

}

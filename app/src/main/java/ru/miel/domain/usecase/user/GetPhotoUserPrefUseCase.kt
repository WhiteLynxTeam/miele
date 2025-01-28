package ru.miel.domain.usecase.user

import ru.miel.domain.istorage.IUserStorage

class GetPhotoUserPrefUseCase(
    private val storage: IUserStorage,
) {
    operator fun invoke(): String? {
        val fullName = storage.getPhotoInfoUser()
        return fullName
    }
}
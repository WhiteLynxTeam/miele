package ru.miel.domain.usecase.user

import ru.miel.domain.istorage.IUserStorage

class GetFullNamePrefUseCase(
    private val storage: IUserStorage,
) {
    operator fun invoke(): String? {
        val fullName = storage.getFullNameInfoUser()
        return fullName
    }
}
package ru.miel.domain.usecase.user

import ru.miel.domain.istorage.IUserStorage
import ru.miel.domain.models.InfoUser

class SaveUserInfoPrefUseCase(
    private val storage: IUserStorage,
) {
    operator fun invoke(infoUser: InfoUser) {
        storage.saveInfoUser(infoUser)
    }
}
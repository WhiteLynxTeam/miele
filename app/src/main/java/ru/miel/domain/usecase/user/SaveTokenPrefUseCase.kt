package ru.miel.domain.usecase.user

import ru.miel.domain.istorage.ITokenStorage

class SaveTokenPrefUseCase(
    private val storage: ITokenStorage
) {
    operator fun invoke(token: String) {
        storage.saveToken(token)
    }
}
package ru.miel.domain.usecase

import ru.miel.domain.istorage.ITokenStorage

class SaveTokenPrefUseCase(
    private val storage: ITokenStorage
) {
    operator fun invoke(token: String) {
        storage.saveToken(token)
    }
}
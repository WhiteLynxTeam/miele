package ru.miel.domain.usecase

import ru.miel.domain.istorage.ITokenStorage
import ru.miel.domain.models.Token

class GetTokenPrefUseCase(
    private val storage: ITokenStorage
) {
    operator fun invoke(): Token {
        val token = storage.getToken()
        return Token(token = token)
    }
}
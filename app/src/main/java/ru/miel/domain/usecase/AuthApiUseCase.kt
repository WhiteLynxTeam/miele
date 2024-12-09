package ru.miel.domain.usecase

import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.models.User

class AuthApiUseCase(
    private val repository: IUserRepository,
    private val saveTokenPrefUseCase: SaveTokenPrefUseCase,
    ) {
    suspend operator fun invoke(user : User): Boolean {

        val result = repository.auth(user)

        if (result.isSuccess) {
            val token = result.getOrNull()
            if (token != null) {
                saveTokenPrefUseCase(token.token)
                return true
            }
        }
        return false
    }
}
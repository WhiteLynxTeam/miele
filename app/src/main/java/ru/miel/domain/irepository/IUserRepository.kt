package ru.miel.domain.irepository

import ru.miel.domain.models.Token
import ru.miel.domain.models.User

interface IUserRepository {
    suspend fun auth(user: User): Result<Token>
}
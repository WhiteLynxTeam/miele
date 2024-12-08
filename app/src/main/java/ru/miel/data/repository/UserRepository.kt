package ru.miel.data.repository

import ru.miel.data.api.UserApi
import ru.miel.data.dto.user.request.AuthUserRequest
import ru.miel.data.dto.user.response.AuthUserResponse
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.models.Token
import ru.miel.domain.models.User

class UserRepository(
    private val userApi: UserApi,
) : IUserRepository {

    override suspend fun auth(user: User): Result<Token> {
        val result = userApi.auth(mapperUserToUserDto(user))
        return result.map { mapperTokenResponseToToken(it) }
    }

    private fun mapperTokenResponseToToken(
        authUserResponse: AuthUserResponse
    ): Token {
        return Token(
            token = authUserResponse.token,
        )
    }

    private fun mapperUserToUserDto(
        user: User
    ): AuthUserRequest {
        return AuthUserRequest(
            username = user.username,
            password = user.password,
        )
    }
}
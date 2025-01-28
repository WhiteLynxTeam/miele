package ru.miel.data.repository

import ru.miel.data.api.UserApi
import ru.miel.data.dto.user.request.AuthUserRequest
import ru.miel.data.dto.user.response.AuthUserResponse
import ru.miel.data.dto.user.response.InfoUserResponse
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.models.InfoUser
import ru.miel.domain.models.Token
import ru.miel.domain.models.User

class UserRepository(
    private val userApi: UserApi,
) : IUserRepository {

    override suspend fun auth(user: User): Result<Token> {
        val result = userApi.auth(mapperUserToUserDto(user))
        return result.map { mapperTokenResponseToToken(it) }
    }

    override suspend fun info(token: Token): Result<InfoUser> {
        val result = userApi.info("Token ${token.token}")
        return result.map { mapperInfoUserDtoToInfoUser(it[0]) }
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

    private fun mapperInfoUserDtoToInfoUser(
        infoUserResponse: InfoUserResponse
    ): InfoUser {
        return InfoUser(
            role = infoUserResponse.role,
            full_name = infoUserResponse.full_name,
            email = infoUserResponse.email,
            phone = infoUserResponse.phone,
            photo = infoUserResponse.photo,
            office_name = infoUserResponse.office_name,
            office_location = infoUserResponse.office_location,
            department = infoUserResponse.department,
            office_quota = infoUserResponse.office_quota,
            office_used_quota = infoUserResponse.office_used_quota,
        )
    }
}
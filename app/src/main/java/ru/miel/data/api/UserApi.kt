package ru.miel.data.api

import retrofit2.http.Body
import retrofit2.http.POST
import ru.miel.data.dto.user.request.AuthUserRequest
import ru.miel.data.dto.user.response.AuthUserResponse

interface UserApi {
    @POST("/api/login/")
    suspend fun auth(
        @Body authUserRequest: AuthUserRequest
    ): Result<AuthUserResponse>
}

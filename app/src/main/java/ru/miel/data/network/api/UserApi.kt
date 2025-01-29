package ru.miel.data.network.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.miel.data.network.dto.user.request.AuthUserRequest
import ru.miel.data.network.dto.user.response.AuthUserResponse
import ru.miel.data.network.dto.user.response.InfoUserResponse

interface UserApi {
    @POST("/api/login/")
    suspend fun auth(
        @Body authUserRequest: AuthUserRequest
    ): Result<AuthUserResponse>

    @GET("/api/info/")
    suspend fun info(
        @Header ("Authorization") token: String
    ): Result<List<InfoUserResponse>>
}

package ru.miel.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import ru.miel.data.dto.user.request.AuthUserRequest
import ru.miel.data.dto.user.response.AuthUserResponse

interface CandidatesApi {
    @GET("/api/v1/candidates/")
    suspend fun getCandidates(): Result<AuthUserResponse>
}

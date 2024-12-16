package ru.miel.data.api

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import ru.miel.data.dto.candidates.request.SetFlagByIdRequest
import ru.miel.data.dto.candidates.response.CandidatesByFlagResponse
import ru.miel.data.dto.candidates.response.CandidatesResponse

interface CandidatesApi {
    @GET("/api/supervisor/candidates/")
    suspend fun getCandidates(
        @Header("Authorization") token: String
        ): Result<List<CandidatesResponse>>

    @GET("/api/supervisor/favorites/")
    suspend fun getFavorites(
        @Header("Authorization") token: String
        ): Result<List<CandidatesByFlagResponse>>

    @POST("/api/supervisor/favorites/")
    suspend fun setFavorite(
        @Header("Authorization") token: String,
        @Body setFlagByIdRequest: SetFlagByIdRequest,
    ): Result<CandidatesByFlagResponse>

    @GET("/api/supervisor/invitations/")
    suspend fun getInvitations(
        @Header("Authorization") token: String
        ): Result<List<CandidatesByFlagResponse>>

    @POST("/api/supervisor/invitations/")
    suspend fun setInvitation(
        @Header("Authorization") token: String,
        @Body setFlagByIdRequest: SetFlagByIdRequest,
        ): Result<CandidatesByFlagResponse>
}

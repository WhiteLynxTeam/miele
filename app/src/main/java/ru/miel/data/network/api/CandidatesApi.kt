package ru.miel.data.network.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import ru.miel.data.network.dto.candidates.request.SetFlagByIdRequest
import ru.miel.data.network.dto.candidates.response.CandidatesByFlagResponse
import ru.miel.data.network.dto.candidates.response.CandidatesResponse

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

    @DELETE("/api/supervisor/favorites/{id}/")
    suspend fun delFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
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

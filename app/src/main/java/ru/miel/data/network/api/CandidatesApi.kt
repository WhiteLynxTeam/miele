package ru.miel.data.network.api

import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.miel.data.network.dto.candidates.CandidatesFilterApi
import ru.miel.data.network.dto.candidates.request.SetFlagByIdRequest
import ru.miel.data.network.dto.candidates.response.FavoritesCandidatesResponse
import ru.miel.data.network.dto.candidates.response.CandidatesResponse
import ru.miel.data.network.dto.candidates.response.InvitationsCandidatesResponse

interface CandidatesApi {
    @GET("/api/supervisor/candidates/")
    suspend fun getCandidates(
        @Header("Authorization") token: String
        ): Result<List<CandidatesResponse>>

    @GET("/api/supervisor/favorites/")
    suspend fun getFavorites(
        @Header("Authorization") token: String
        ): Result<List<FavoritesCandidatesResponse>>

    @POST("/api/supervisor/favorites/")
    suspend fun setFavorite(
        @Header("Authorization") token: String,
        @Body setFlagByIdRequest: SetFlagByIdRequest,
    ): Result<FavoritesCandidatesResponse>

    @DELETE("/api/supervisor/favorites/{id}/")
    suspend fun delFavorite(
        @Header("Authorization") token: String,
        @Path("id") id: Int,
    ): Result<FavoritesCandidatesResponse>

    @GET("/api/supervisor/invitations/")
    suspend fun getInvitations(
        @Header("Authorization") token: String
        ): Result<List<InvitationsCandidatesResponse>>

    @POST("/api/supervisor/invitations/")
    suspend fun setInvitation(
        @Header("Authorization") token: String,
        @Body setFlagByIdRequest: SetFlagByIdRequest,
        ): Result<InvitationsCandidatesResponse>

    @GET("/api/supervisor/candidates/")
    suspend fun getCandidatesFilter(
        @Header("Authorization") token: String,
        @Query("age") age: Int?,
        @Query("age_min") age_min: Int?,
        @Query("age_max") age_max: Int?,
        @Query("courses", encoded = true) courses: String?,
    ): Result<List<CandidatesResponse>>


}

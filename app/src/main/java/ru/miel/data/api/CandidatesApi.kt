package ru.miel.data.api

import retrofit2.http.GET
import retrofit2.http.Header
import ru.miel.data.dto.candidates.response.CandidatesResponse

interface CandidatesApi {
    @GET("/api/supervisor/candidates/")
    suspend fun getCandidates(
        @Header("Authorization") token: String
        ): Result<List<CandidatesResponse>>
}

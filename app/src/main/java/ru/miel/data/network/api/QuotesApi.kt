package ru.miel.data.network.api

import retrofit2.http.GET
import retrofit2.http.Header
import ru.miel.data.network.dto.quotes.QuotesResponse

interface QuotesApi {
    @GET("/api/info/")
    suspend fun quotes(
        @Header ("Authorization") token: String
    ): Result<List<QuotesResponse>>
}

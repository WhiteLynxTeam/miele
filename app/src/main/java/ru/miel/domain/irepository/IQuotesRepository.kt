package ru.miel.domain.irepository

import ru.miel.domain.models.AddQuotes
import ru.miel.domain.models.Quotes
import ru.miel.domain.models.StatisticQuotes
import ru.miel.domain.models.Token

interface IQuotesRepository {
    suspend fun createQuotes(candidates: List<Quotes>): Boolean

    suspend fun getQuotesDao() : Int
    suspend fun minusQuotesDao(): Boolean

    suspend fun getQuotesApi(token: Token): Result<Quotes>

    suspend fun getStatisticQuotesApi(token: Token): Result<List<StatisticQuotes>>
    suspend fun getStatisticQuotesNowYearApi(token: Token): Result<List<StatisticQuotes>>

    suspend fun addQuotesApi(token: Token, numQuotes: Int): Result<AddQuotes>
}
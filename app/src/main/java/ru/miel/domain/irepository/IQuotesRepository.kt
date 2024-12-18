package ru.miel.domain.irepository

import ru.miel.domain.models.Quotes

interface IQuotesRepository {
    suspend fun createQuotes(candidates: List<Quotes>): Boolean

    suspend fun getQuotesDao() : Int
    suspend fun minusQuotesDao(): Boolean
}
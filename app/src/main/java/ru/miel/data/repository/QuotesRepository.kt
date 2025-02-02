package ru.miel.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miel.data.dbo.dao.QuotesDao
import ru.miel.data.dbo.entity.QuotesEntity
import ru.miel.data.network.api.QuotesApi
import ru.miel.data.network.dto.quotes.QuotesResponse
import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.models.Quotes
import ru.miel.domain.models.Token
import java.time.ZoneOffset

class QuotesRepository(
    private val quotesDao: QuotesDao,
    private val quotesApi: QuotesApi,
    ) : IQuotesRepository {
    override suspend fun createQuotes(quotes: List<Quotes>): Boolean {
        val quotesDb = mapperQuotesToQuotesEntity(quotes)
        withContext(Dispatchers.IO) {
            quotesDao.trunc()
            quotesDao.insertAll(quotesDb)
        }
        return true
    }

    override suspend fun getQuotesDao(): Int {
        val quotes = withContext(Dispatchers.IO) {
            quotesDao.getQuotes(System.currentTimeMillis())
        }
        return quotes
    }

    override suspend fun minusQuotesDao(): Boolean {
        withContext(Dispatchers.IO) {
            quotesDao.minusQuotes(System.currentTimeMillis())
        }
        return true
    }

    override suspend fun getQuotesApi(token: Token): Result<Quotes> {
        val result = quotesApi.quotes("Token ${token.token}")
        return result.map { mapperQuotesDtoToQuotes(it[0]) }
    }

    private fun mapperQuotesToQuotesEntity(
        quotes: List<Quotes>
    ): List<QuotesEntity> {
        return quotes.map {
            QuotesEntity(
//                start_date = it.startDate?.toStringByFormat(DATE_PATTERN_DEFAULT)?:"",
//                end_date = it.endDate?.toStringByFormat(DATE_PATTERN_DEFAULT)?:"",
                start_date = it.startDate?.atTime(0, 0, 0)?.toInstant(ZoneOffset.UTC)
                    ?.toEpochMilli()
                    ?: 0L,
                end_date = it.endDate?.atTime(23, 59, 59)?.toInstant(ZoneOffset.UTC)
                    ?.toEpochMilli()
                    ?: 0L,
                quotes = it.quotesUsed,
                quotes_remaining = it.quotes,
            )
        }
    }


    private fun mapperQuotesDtoToQuotes(
        quotesResponse: QuotesResponse
    ): Quotes {
        return Quotes(
            quotesUsed = quotesResponse.office_used_quota.toInt(),
            quotes = quotesResponse.office_quota.toInt(),
        )
    }
}

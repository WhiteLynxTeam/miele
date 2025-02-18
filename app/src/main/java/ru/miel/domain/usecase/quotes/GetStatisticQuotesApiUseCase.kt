package ru.miel.domain.usecase.quotes

import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.models.Quotes
import ru.miel.domain.models.StatisticQuotes
import ru.miel.domain.usecase.user.GetTokenPrefUseCase
import kotlin.Int

class GetStatisticQuotesApiUseCase(
    private val repository: IQuotesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): Quotes? {
        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return null

        val result = repository.getStatisticQuotesApi(token)

        if (result.isSuccess) {
            val quotes = result.getOrNull()
            if (quotes != null) {
                return Quotes(
                    quotesUsed = quotes.sumOf { it.invited },
                    quotes = quotes.sumOf { it.issued } )

            }
        }
        return null
    }
}
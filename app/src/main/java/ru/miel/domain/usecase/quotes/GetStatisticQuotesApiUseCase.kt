package ru.miel.domain.usecase.quotes

import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.models.Quotes
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class GetStatisticQuotesApiUseCase(
    private val repository: IQuotesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): Quotes? {
        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return null

//        val result = repository.getStatisticQuotesApi(token)
        val result = repository.getStatisticQuotesNowYearApi(token)

        if (result.isSuccess) {
            val quotes = result.getOrNull()
            if (quotes != null) {

                val (sumIssued, sumInvited) = quotes.fold(0 to 0) { sum, quote ->
                    sum.first + quote.issued to sum.second + quote.invited
                }

                return Quotes(quotesUsed = sumInvited, quotes = sumIssued)
            }
        }
        return null
    }
}
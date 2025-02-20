package ru.miel.domain.usecase.quotes

import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class AddQuotesApiUseCase(
    private val repository: IQuotesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(numQuotes: Int): Pair<Boolean,Int> {
        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return Pair(false,0)

        val result = repository.addQuotesApi(token, numQuotes)

        if (result.isSuccess) {
            val infoQuote = result.getOrNull()
            if (infoQuote != null) {
                return Pair(true, infoQuote.amount)
            }
        }

        return Pair(false,0)
    }
}
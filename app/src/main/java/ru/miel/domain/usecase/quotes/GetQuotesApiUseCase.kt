package ru.miel.domain.usecase.quotes

import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.models.Quotes
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class GetQuotesApiUseCase(
    private val repository: IQuotesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): Quotes? {
        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return null

        val result = repository.getQuotesApi(token)

        if (result.isSuccess) {
            val quotes = result.getOrNull()
            if (quotes != null) {
                return quotes
            }
        }

        return null
    }
}
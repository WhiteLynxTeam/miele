package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.IQuotesRepository

class GetQuotesByNowDbUseCase(
    private val repository: IQuotesRepository,
) {
    suspend operator fun invoke(): Int  {
        val quotes = repository.getQuotesDao()
        return quotes
    }
}
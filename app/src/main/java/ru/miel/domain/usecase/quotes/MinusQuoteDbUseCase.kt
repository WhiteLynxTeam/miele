package ru.miel.domain.usecase.quotes

import ru.miel.domain.irepository.IQuotesRepository

class MinusQuoteDbUseCase(
    private val repository: IQuotesRepository,
) {
    suspend operator fun invoke(): Boolean  {
        val quotes = repository.minusQuotesDao()
        return quotes
    }
}
package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.sampleListOfCandidates
import ru.miel.domain.sampleListOfQuotes

class FilDbWithSampleDataUseCase(
    private val candidatesRepository: ICandidatesRepository,
    private val quotesRepository: IQuotesRepository,
) {
    suspend operator fun invoke(): Boolean {
        candidatesRepository.createCandidates(sampleListOfCandidates)
        quotesRepository.createQuotes(sampleListOfQuotes)
        return true
    }
}

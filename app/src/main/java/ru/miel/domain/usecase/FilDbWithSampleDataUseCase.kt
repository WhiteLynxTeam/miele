package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.Candidates

class FilDbWithSampleDataUseCase(
    private val repository: ICandidatesRepository,
) {
    suspend operator fun invoke(candidates: List<Candidates>): Boolean  {
        val result = repository.createCandidates(candidates)
        return result
    }
}
package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.Candidates

class GetCandidatesDbUseCase(
    private val repository: ICandidatesRepository,
) {
    suspend operator fun invoke(): List<Candidates>  {
        val result = repository.getCandidatesDao()
        return result
    }
}
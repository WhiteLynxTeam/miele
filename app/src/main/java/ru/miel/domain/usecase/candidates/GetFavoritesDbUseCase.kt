package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.Candidates

class GetFavoritesDbUseCase(
    private val repository: ICandidatesRepository,
) {
    suspend operator fun invoke(): List<Candidates>  {
        val result = repository.getFavoritesDao()
        return result
    }
}
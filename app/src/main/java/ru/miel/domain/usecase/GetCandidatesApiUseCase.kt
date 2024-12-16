package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFromApi

class GetCandidatesApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
    private val getFavoritesApiUseCase: GetFavoritesApiUseCase,
) {
    suspend operator fun invoke(): List<CandidatesFromApi>  {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return emptyList()

        val result = repository.getCandidatesApi(token)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {

                val favoriteIds = getFavoritesApiUseCase(token).map { it.id }

                candidates.forEach { candidate ->
                    candidate.isFavorite = candidate.id in favoriteIds
                }

                return candidates
            }
        }
        return emptyList()
    }
}
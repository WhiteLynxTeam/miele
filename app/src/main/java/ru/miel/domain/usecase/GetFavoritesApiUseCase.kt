package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFlagFromApi
import ru.miel.domain.models.Token

class GetFavoritesApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): List<CandidatesFlagFromApi>  {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return emptyList()

        val result = repository.getFavoritesApi(token)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {
                return candidates
            }
        }
        return emptyList()
    }

    suspend operator fun invoke(token: Token): List<CandidatesFlagFromApi>  {

        if (token.token.isEmpty()) return emptyList()

        val result = repository.getFavoritesApi(token)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {
                return candidates
            }
        }
        return emptyList()
    }
}
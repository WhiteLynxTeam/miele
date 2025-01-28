package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.IdCandidateFromApi
import ru.miel.domain.models.Token
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class GetFavoritesApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): List<IdCandidateFromApi>  {

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

    suspend operator fun invoke(token: Token): List<IdCandidateFromApi>  {

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
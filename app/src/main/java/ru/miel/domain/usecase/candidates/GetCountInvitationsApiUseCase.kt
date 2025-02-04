package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class GetCountInvitationsApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
    private val getInvitationsApiUseCase: GetInvitationsApiUseCase,
) {
    suspend operator fun invoke(): Int {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return 0

        val result = repository.getInvitationsApi(token)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {

/*                val favoriteIds = getFavoritesApiUseCase(token).map { it.id }

                candidates.forEach { candidate ->
                    candidate.isFavorite = candidate.id in favoriteIds
                }*/

                return candidates.size
            }
        }
        return 0
    }
}
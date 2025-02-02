package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class SetInvitationsApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(idCandidate: Int): Boolean  {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return false

        val result = repository.setInvitationApi(token, idCandidate)
        return result.isSuccess
    }
}
package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class SetFavoriteApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(id: Int): Boolean {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return false

        return repository.setFavoriteApi(token, id)
    }
}
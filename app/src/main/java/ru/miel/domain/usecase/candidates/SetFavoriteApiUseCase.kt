package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class SetFavoriteApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(id: Int, flag: Boolean, idFavorite: Int): Boolean {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return false

        return if (flag) repository.delFavoriteApi(token, idFavorite)
        else repository.setFavoriteApi(token, id)
    }
}
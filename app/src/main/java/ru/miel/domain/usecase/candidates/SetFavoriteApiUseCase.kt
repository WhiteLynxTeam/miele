package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class SetFavoriteApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(id: Int, flag: Boolean, idFavorite: Int?): Boolean {

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return false

        return if (flag) {
            if (idFavorite != null) repository.delFavoriteApi(token, idFavorite)
            else {
                /***Сюда логика никогда не должна зайти, потому что на удаление фаворита
                 * всегда будет idFavorite. На всякий случай
                 * внедрить в функцию exception*/
                return false
            }
        }
        else repository.setFavoriteApi(token, id)
    }
}
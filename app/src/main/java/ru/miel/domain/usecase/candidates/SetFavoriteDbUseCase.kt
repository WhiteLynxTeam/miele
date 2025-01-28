package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository

class SetFavoriteDbUseCase(
    private val repository: ICandidatesRepository,
) {
    suspend operator fun invoke(id: Int, flag: Boolean): Boolean {
        if (flag) {
            repository.delFavoriteDao(id)
        } else {
            repository.setFavoriteDao(id)
        }
        return true
    }
}
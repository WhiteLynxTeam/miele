package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository

class SetInvitationDbUseCase(
    private val repository: ICandidatesRepository,
) {
    suspend operator fun invoke(id: Int, flag: Boolean): Boolean {
        if (flag) {
            repository.delInvitationDao(id)
        } else {
            repository.setInvitationDao(id)
        }
        return true
    }
}
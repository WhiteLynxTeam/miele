package ru.miel.domain.usecase

import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.models.Token
import ru.miel.domain.models.User

class CheckRoleApiUseCase(

    private val repository: IUserRepository,
) {

    suspend operator fun invoke(token: Token): Boolean {

        val result = repository.info(token)

        if (result.isSuccess) {
            val infoUser = result.getOrNull()
            if (infoUser != null) {
                //Заменить "2" на enum class с ролями где "2" это руководители
                if (infoUser.role == "2") return true
            }
        }
        return false
    }
}


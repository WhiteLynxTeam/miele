package ru.miel.domain.usecase

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFromApi

class GetCandidatesApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(): List<CandidatesFromApi>  {

        val token = getTokenPrefUseCase()
        if (token.token.isNullOrEmpty()) return emptyList()

        val result = repository.getCandidatesApi(token)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {
                return candidates
            }
        }
        return emptyList()
    }
}
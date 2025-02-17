package ru.miel.domain.usecase.candidates

import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.CandidatesFilter
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.usecase.user.GetTokenPrefUseCase

class GetCandidatesFilterApiUseCase(
    private val repository: ICandidatesRepository,
    private val getTokenPrefUseCase: GetTokenPrefUseCase,
) {
    suspend operator fun invoke(candidatesFilter: CandidatesFilter): List<CandidatesFromApi> {

        var flagIsEmptyCourses = IsEmptyCourses(candidatesFilter)
        var flagIsNullAge = IsNullAge(candidatesFilter)

        /***Red flag Написать исключение, если неправильные поля по возрасту
         * и все курсы не выбраны
         * */

        val token = getTokenPrefUseCase()
        if (token.token.isEmpty()) return emptyList()

        val result = repository.getCandidatesFilterApi(token, candidatesFilter)

        if (result.isSuccess) {
            val candidates = result.getOrNull()
            if (candidates != null) {

                return candidates
            }
        }
        return emptyList()
    }

    private fun IsEmptyCourses(candidatesFilter: CandidatesFilter): Boolean {
        return candidatesFilter.course_rieltor_join || candidatesFilter.basic_legal_course || candidatesFilter.course_mortgage || candidatesFilter.course_taxation
    }

    private fun IsNullAge(candidatesFilter: CandidatesFilter): Boolean {
        return candidatesFilter.age_min == null && candidatesFilter.age_max == null
    }
}
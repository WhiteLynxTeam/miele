package ru.miel.domain.irepository

import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.Token

interface ICandidatesRepository {
    suspend fun createCandidates(candidates: List<Candidates>): Boolean
    suspend fun getCandidatesDao() : List<Candidates>
    suspend fun getCandidatesApi(token: Token) : Result<List<CandidatesFromApi>>
}
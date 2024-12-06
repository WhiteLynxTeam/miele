package ru.miel.domain.irepository

import ru.miel.domain.models.Candidates

interface ICandidatesRepository {
    suspend fun createCandidates(candidates: List<Candidates>): Boolean
    suspend fun getCandidates() : List<Candidates>
}
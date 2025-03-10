package ru.miel.domain.irepository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFilter
import ru.miel.domain.models.IdCandidateFromApi
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.domain.models.Token

interface ICandidatesRepository {
    suspend fun createCandidates(candidates: List<Candidates>): Boolean

    suspend fun getCandidatesDao() : List<Candidates>
    suspend fun getFavoritesDao(): List<Candidates>
    suspend fun setFavoriteDao(id: Int): Boolean
    suspend fun delFavoriteDao(id: Int): Boolean
    suspend fun setInvitationDao(id: Int): Boolean
    suspend fun delInvitationDao(id: Int): Boolean

    suspend fun getCandidatesApi(token: Token) : Result<List<CandidatesFromApi>>
    suspend fun getFavoritesApi(token: Token) : Result<List<IdCandidateFromApi>>
    suspend fun getInvitationsApi(token: Token): Result<List<InvitationsCandidatesFromApi>>
    suspend fun setFavoriteApi(token: Token, id: Int) : Boolean
    suspend fun setInvitationApi(token: Token, id: Int): Result<InvitationsCandidatesFromApi>
    suspend fun delFavoriteApi(token: Token, id: Int): Boolean
    suspend fun getCandidatesFilterApi(token: Token , candidatesFilter: CandidatesFilter) : Result<List<CandidatesFromApi>>
}
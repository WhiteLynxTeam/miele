package ru.miel.data.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.dbo.entity.CandidatesEntity
import ru.miel.data.network.api.CandidatesApi
import ru.miel.data.network.dto.candidates.request.SetFlagByIdRequest
import ru.miel.data.network.dto.candidates.response.CandidatesResponse
import ru.miel.data.network.dto.candidates.response.FavoritesCandidatesResponse
import ru.miel.data.network.dto.candidates.response.InvitationsCandidatesResponse
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.enummodel.CourseStatus
import ru.miel.domain.models.IdCandidateFromApi
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.domain.models.Token
import ru.miel.domain.models.enummodel.InvitationStatus
import ru.miel.utils.randomUuid

class CandidatesRepository(
    private val candidatesDao: CandidatesDao,
    private val candidatesApi: CandidatesApi,
) : ICandidatesRepository {
    override suspend fun createCandidates(candidates: List<Candidates>): Boolean {
        val candidatesDb = mapperCandidatesToCandidatesEntity(candidates)
        candidatesDb.randomUuid()
        withContext(Dispatchers.IO) {
            candidatesDao.trunc()
            candidatesDao.insertAll(candidatesDb)
        }
        return true
    }

    override suspend fun getCandidatesDao(): List<Candidates> {
        val candidates = withContext(Dispatchers.IO) {
            candidatesDao.getCandidates()
        }
        return mapperCandidatesEntityToCandidates(candidates)
    }

    override suspend fun getFavoritesDao(): List<Candidates> {
        val candidates = withContext(Dispatchers.IO) {
            candidatesDao.getFavorites()
        }
        return mapperCandidatesEntityToCandidates(candidates)
    }

    override suspend fun setFavoriteDao(id: Int): Boolean {
        withContext(Dispatchers.IO) {
            candidatesDao.setFavorite(id)
        }
        return true
    }

    override suspend fun delFavoriteDao(id: Int): Boolean {
        withContext(Dispatchers.IO) {
            candidatesDao.delFavorite(id)
        }
        return true
    }

    override suspend fun setInvitationDao(id: Int): Boolean {
        withContext(Dispatchers.IO) {
            candidatesDao.setInvitation(id)
        }
        return true
    }

    override suspend fun delInvitationDao(id: Int): Boolean {
        withContext(Dispatchers.IO) {
            candidatesDao.delInvitation(id)
        }
        return true
    }

    override suspend fun getCandidatesApi(token: Token): Result<List<CandidatesFromApi>> {
        val result = candidatesApi.getCandidates("Token ${token.token}")
        return result.map { mapperCandidatesDtoToCandidates(it) }
    }

    override suspend fun getFavoritesApi(token: Token): Result<List<IdCandidateFromApi>> {
        val result = candidatesApi.getFavorites("Token ${token.token}")
        return result.map { mapperIdCandidatesDtoToIdCandidates(it) }
    }

    override suspend fun getInvitationsApi(token: Token): Result<List<InvitationsCandidatesFromApi>> {
        val result = candidatesApi.getInvitations("Token ${token.token}")
        return result.map { mapperListInvitationsCandidatesDtoToListInvitationsCandidates(it) }
    }

    override suspend fun setFavoriteApi(token: Token, id: Int): Boolean {
        val result = candidatesApi.setFavorite("Token ${token.token}", SetFlagByIdRequest(id))
        return result.isSuccess
    }

    override suspend fun delFavoriteApi(token: Token, id: Int): Boolean {
        val result = candidatesApi.delFavorite("Token ${token.token}", id)
        return result.isSuccess
    }

    override suspend fun setInvitationApi(
        token: Token,
        id: Int
    ): Result<InvitationsCandidatesFromApi> {
        val result = candidatesApi.setInvitation("Token ${token.token}", SetFlagByIdRequest(id))
        return result.map { mapperInvitationsCandidateDtoToInvitationsCandidate(it) }
    }

    private fun mapperCandidatesToCandidatesEntity(
        candidates: List<Candidates>
    ): List<CandidatesEntity> {
        return candidates.map {
            CandidatesEntity(
                img = it.img,
                name = it.name,
                year = it.year,
                city = it.city,
                realtor = it.realtor,
                juridicalCourse = it.juridicalCourse,
                mortgage = it.mortgage,
                taxation = it.taxation,
                objects = it.objects,
                clients = it.clients,
                isInvite = it.isInvite,
                isFavorite = it.isFavorite,
            )
        }
    }

    private fun mapperCandidatesEntityToCandidates(
        candidates: List<CandidatesEntity>
    ): List<Candidates> {
        return candidates.map {
            Candidates(
                id = it.id,
                uuid = it.uuid,
                img = it.img,
                name = it.name,
                year = it.year,
                city = it.city,
                realtor = it.realtor,
                juridicalCourse = it.juridicalCourse,
                mortgage = it.mortgage,
                taxation = it.taxation,
                objects = it.objects,
                clients = it.clients,
                isInvite = it.isInvite,
                isFavorite = it.isFavorite,
            )
        }
    }

    private fun mapperCandidatesDtoToCandidates(
        candidates: List<CandidatesResponse>
    ): List<CandidatesFromApi> {
        return candidates.map {
            CandidatesFromApi(
                id = it.id,
                name = it.name,
                surname = it.surname,
                patronymic = it.patronymic,
                birth = it.birth,
                age = it.age,
                education = it.education,
                photo = it.photo,
                country = it.country,
                city = it.city,
                resume = it.resume,
                course_rieltor_join = CourseStatus.fromValue(it.course_rieltor_join),
                basic_legal_course = CourseStatus.fromValue(it.basic_legal_course),
                course_mortgage = CourseStatus.fromValue(it.course_mortgage),
                course_taxation = CourseStatus.fromValue(it.course_taxation),
                completed_objects = it.completed_objects,
                clients = it.clients,
                updated_at = it.updated_at,
                isFavorite = it.is_favorite,
                favorite_id = it.favorite_id,
                isInvited = it.is_invited,
            )
        }
    }

    private fun mapperIdCandidatesDtoToIdCandidates(
        candidates: List<FavoritesCandidatesResponse>
    ): List<IdCandidateFromApi> {
        return candidates.map {
            IdCandidateFromApi(
                id = it.id,
            )
        }
    }


    private fun mapperListInvitationsCandidatesDtoToListInvitationsCandidates(
        invitationsCandidatesResponse: List<InvitationsCandidatesResponse>
    ): List<InvitationsCandidatesFromApi> {
        return invitationsCandidatesResponse.map {
            InvitationsCandidatesFromApi(
                candidateId = it.candidate,
                name = it.name,
                surname = it.surname,
                patronymic = it.patronymic,
                city = it.city,
                age = it.age,
                status = InvitationStatus.fromValue(it.status),
                updatedAt = it.updated_at,
            )
        }
    }

    private fun mapperInvitationsCandidateDtoToInvitationsCandidate(
        invitationsCandidatesResponse: InvitationsCandidatesResponse
    ): InvitationsCandidatesFromApi {
        return InvitationsCandidatesFromApi(
            candidateId = invitationsCandidatesResponse.candidate,
            name = invitationsCandidatesResponse.name,
            surname = invitationsCandidatesResponse.surname,
            patronymic = invitationsCandidatesResponse.patronymic,
            city = invitationsCandidatesResponse.city,
            age = invitationsCandidatesResponse.age,
            status = InvitationStatus.fromValue(invitationsCandidatesResponse.status),
            updatedAt = invitationsCandidatesResponse.updated_at,
        )

    }
}
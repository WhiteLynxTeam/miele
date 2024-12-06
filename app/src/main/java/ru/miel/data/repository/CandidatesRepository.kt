package ru.miel.data.repository

import androidx.room.ColumnInfo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.dbo.entity.CandidatesEntity
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.models.Candidates
import ru.miel.utils.randomUuid

class CandidatesRepository(
    private val candidatesDao: CandidatesDao,
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

    override suspend fun getCandidates() : List<Candidates> {
        val candidates = withContext(Dispatchers.IO) {
            candidatesDao.getCandidates()
        }
        return mapperCandidatesEntityToCandidates(candidates)
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
}
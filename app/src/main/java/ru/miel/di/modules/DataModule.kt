package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.repository.CandidatesRepository
import ru.miel.domain.irepository.ICandidatesRepository
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideCandidatesRepository(
        candidatesDao: CandidatesDao
    ) : ICandidatesRepository {
        return CandidatesRepository(
            candidatesDao = candidatesDao
        )
    }
}
package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideFilDbWithSampleDataUseCase(
        repository: ICandidatesRepository,
    ): FilDbWithSampleDataUseCase {
        return FilDbWithSampleDataUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCandidatesDbUseCase(
        repository: ICandidatesRepository,
    ): GetCandidatesDbUseCase {
        return GetCandidatesDbUseCase(
            repository = repository,
        )
    }
}
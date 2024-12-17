package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.istorage.ITokenStorage
import ru.miel.domain.usecase.AuthApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesApiUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.domain.usecase.GetFavoritesApiUseCase
import ru.miel.domain.usecase.GetFavoritesDbUseCase
import ru.miel.domain.usecase.GetTokenPrefUseCase
import ru.miel.domain.usecase.SaveTokenPrefUseCase
import ru.miel.domain.usecase.SetFavoriteDbUseCase
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

    @Singleton
    @Provides
    fun provideGetFavoritesDbUseCase(
        repository: ICandidatesRepository,
    ): GetFavoritesDbUseCase {
        return GetFavoritesDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideSetFavoriteDbUseCase(
        repository: ICandidatesRepository,
    ): SetFavoriteDbUseCase {
        return SetFavoriteDbUseCase(
            repository = repository,
        )
    }

    @Singleton
    @Provides
    fun provideGetCandidatesApiUseCase(
        repository: ICandidatesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
        getFavoritesApiUseCase: GetFavoritesApiUseCase,
    ): GetCandidatesApiUseCase {
        return GetCandidatesApiUseCase(
            repository = repository,
            getTokenPrefUseCase = getTokenPrefUseCase,
            getFavoritesApiUseCase = getFavoritesApiUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideGetFavoritesApiUseCase(
        repository: ICandidatesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
    ): GetFavoritesApiUseCase {
        return GetFavoritesApiUseCase(
            repository = repository,
            getTokenPrefUseCase = getTokenPrefUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideAuthApiUseCase(
        repository: IUserRepository,
        saveTokenPrefUseCase: SaveTokenPrefUseCase,
    ): AuthApiUseCase {
        return AuthApiUseCase(
            repository = repository,
            saveTokenPrefUseCase = saveTokenPrefUseCase
        )
    }

    @Singleton
    @Provides
    fun provideSaveTokenPrefUseCase(
        storage: ITokenStorage,
    ): SaveTokenPrefUseCase {
        return SaveTokenPrefUseCase(storage = storage)
    }

    @Singleton
    @Provides
    fun provideGetTokenPrefUseCase(
        storage: ITokenStorage,
    ): GetTokenPrefUseCase {
        return GetTokenPrefUseCase(storage = storage)
    }
}
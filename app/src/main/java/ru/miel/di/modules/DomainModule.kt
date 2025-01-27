package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.istorage.ITokenStorage
import ru.miel.domain.usecase.AuthApiUseCase
import ru.miel.domain.usecase.CheckRoleApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesApiUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.domain.usecase.GetFavoritesApiUseCase
import ru.miel.domain.usecase.GetFavoritesDbUseCase
import ru.miel.domain.usecase.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.GetTokenPrefUseCase
import ru.miel.domain.usecase.MinusQuoteDbUseCase
import ru.miel.domain.usecase.SaveTokenPrefUseCase
import ru.miel.domain.usecase.SetFavoriteDbUseCase
import ru.miel.domain.usecase.SetInvitationDbUseCase
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideFilDbWithSampleDataUseCase(
        candidatesRepository: ICandidatesRepository,
        quotesRepository: IQuotesRepository,
    ): FilDbWithSampleDataUseCase {
        return FilDbWithSampleDataUseCase(
            candidatesRepository = candidatesRepository,
            quotesRepository = quotesRepository,
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
    fun provideSetInvitationDbUseCase(
        repository: ICandidatesRepository,
    ): SetInvitationDbUseCase {
        return SetInvitationDbUseCase(
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
        checkRoleApiUseCase: CheckRoleApiUseCase,

        ): AuthApiUseCase {
        return AuthApiUseCase(
            repository = repository,
            saveTokenPrefUseCase = saveTokenPrefUseCase,
            checkRoleApiUseCase = checkRoleApiUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideCheckRoleApiUseCase(
        repository: IUserRepository,

        ): CheckRoleApiUseCase {
        return CheckRoleApiUseCase(
            repository = repository,
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

    @Singleton
    @Provides
    fun provideGetQuotesByNowDbUseCase(
        repository: IQuotesRepository,
    ): GetQuotesByNowDbUseCase {
        return GetQuotesByNowDbUseCase(repository = repository)
    }

    @Singleton
    @Provides
    fun provideMinusQuoteDbUseCase(
        repository: IQuotesRepository,
    ): MinusQuoteDbUseCase {
        return MinusQuoteDbUseCase(repository = repository)
    }
}
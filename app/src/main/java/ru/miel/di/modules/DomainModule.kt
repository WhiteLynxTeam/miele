package ru.miel.di.modules

import dagger.Module
import dagger.Provides
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.istorage.ITokenStorage
import ru.miel.domain.istorage.IUserStorage
import ru.miel.domain.usecase.user.AuthApiUseCase
import ru.miel.domain.usecase.user.CheckRoleApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesDbUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesApiUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesDbUseCase
import ru.miel.domain.usecase.candidates.GetInvitationsApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.user.GetTokenPrefUseCase
import ru.miel.domain.usecase.quotes.MinusQuoteDbUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteApiUseCase
import ru.miel.domain.usecase.user.SaveTokenPrefUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationsApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesApiUseCase
import ru.miel.domain.usecase.user.GetFullNamePrefUseCase
import ru.miel.domain.usecase.user.GetPhotoUserPrefUseCase
import ru.miel.domain.usecase.user.SaveUserInfoPrefUseCase
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
    fun provideGetInvitationsApiUseCase(
        repository: ICandidatesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
    ): GetInvitationsApiUseCase {
        return GetInvitationsApiUseCase(
            repository = repository,
            getTokenPrefUseCase = getTokenPrefUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideSetInvitationsApiUseCase(
        repository: ICandidatesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
    ): SetInvitationsApiUseCase {
        return SetInvitationsApiUseCase(
            repository = repository,
            getTokenPrefUseCase = getTokenPrefUseCase,
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
    fun provideGetQuotesApiUseCase(
        repository: IQuotesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
    ): GetQuotesApiUseCase {
        return GetQuotesApiUseCase(
            repository = repository,
            getTokenPrefUseCase = getTokenPrefUseCase,
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
    fun provideSetFavoriteApiUseCase(
        repository: ICandidatesRepository,
        getTokenPrefUseCase: GetTokenPrefUseCase,
    ): SetFavoriteApiUseCase {
        return SetFavoriteApiUseCase(
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
        saveUserInfoPrefUseCase: SaveUserInfoPrefUseCase,
        ): CheckRoleApiUseCase {
        return CheckRoleApiUseCase(
            repository = repository,
            saveUserInfoPrefUseCase = saveUserInfoPrefUseCase,
        )
    }

    @Singleton
    @Provides
    fun provideSaveUserInfoPrefUseCase(
        storage: IUserStorage,
        ): SaveUserInfoPrefUseCase {
        return SaveUserInfoPrefUseCase(
            storage = storage,
        )
    }

    @Singleton
    @Provides
    fun provideGetFullNamePrefUseCase(
        storage: IUserStorage,
        ): GetFullNamePrefUseCase {
        return GetFullNamePrefUseCase(
            storage = storage,
        )
    }

    @Singleton
    @Provides
    fun provideGetPhotoUserPrefUseCase(
        storage: IUserStorage,
        ): GetPhotoUserPrefUseCase {
        return GetPhotoUserPrefUseCase(
            storage = storage,
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
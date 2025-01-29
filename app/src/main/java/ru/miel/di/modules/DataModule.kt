package ru.miel.di.modules

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.miel.data.network.api.CandidatesApi
import ru.miel.data.network.api.UserApi
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.dbo.dao.QuotesDao
import ru.miel.data.repository.CandidatesRepository
import ru.miel.data.repository.QuotesRepository
import ru.miel.data.repository.UserRepository
import ru.miel.data.storage.TokenStorage
import ru.miel.data.storage.UserStorage
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IQuotesRepository
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.istorage.ITokenStorage
import ru.miel.domain.istorage.IUserStorage
import javax.inject.Singleton

@Module
class DataModule {
    @Provides
    @Singleton
    fun provideCandidatesRepository(
        candidatesDao: CandidatesDao,
        candidatesApi: CandidatesApi,
    ) : ICandidatesRepository {
        return CandidatesRepository(
            candidatesDao = candidatesDao,
            candidatesApi = candidatesApi,
        )
    }

    @Provides
    @Singleton
    fun provideQuotesRepository(
        quotesDao: QuotesDao,
    ) : IQuotesRepository {
        return QuotesRepository(
            quotesDao = quotesDao,
        )
    }

    @Provides
    @Singleton
    fun provideUserRepository(
        userApi: UserApi
    ) : IUserRepository {
        return UserRepository(
            userApi = userApi
        )
    }

    @Provides
    @Singleton
    fun provideTokenStorage(
        sharedPreferences: SharedPreferences,
    ): ITokenStorage {
        return TokenStorage(
            sharedPreferences = sharedPreferences,
        )
    }

    @Provides
    @Singleton
    fun provideUserStorage(
        sharedPreferences: SharedPreferences,
    ): IUserStorage {
        return UserStorage(
            sharedPreferences = sharedPreferences,
        )
    }
}
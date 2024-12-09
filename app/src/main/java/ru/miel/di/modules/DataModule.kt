package ru.miel.di.modules

import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import ru.miel.data.api.UserApi
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.repository.CandidatesRepository
import ru.miel.data.repository.UserRepository
import ru.miel.data.storage.TokenStorage
import ru.miel.domain.irepository.ICandidatesRepository
import ru.miel.domain.irepository.IUserRepository
import ru.miel.domain.istorage.ITokenStorage
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
}
package ru.miel.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.miel.data.dbo.db.MielDataBase
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDb(context: Context) = Room.databaseBuilder(
        context, MielDataBase::class.java, "miel_db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideCandidatesDao(context: Context) = provideDb(context).candidatesDao()

}


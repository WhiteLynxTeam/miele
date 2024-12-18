package ru.miel.data.dbo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.dbo.dao.QuotesDao
import ru.miel.data.dbo.entity.CandidatesEntity
import ru.miel.data.dbo.entity.QuotesEntity

@Database(
    entities = [
        CandidatesEntity::class,
        QuotesEntity::class,
    ],
    version = 6,
    exportSchema = false
)

abstract class MielDataBase : RoomDatabase() {
    abstract fun candidatesDao(): CandidatesDao
    abstract fun quotesDao(): QuotesDao
}
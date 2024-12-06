package ru.miel.data.dbo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.miel.data.dbo.dao.CandidatesDao
import ru.miel.data.dbo.entity.CandidatesEntity


@Database(
    entities = [
        CandidatesEntity::class
    ],
    version = 1,
    exportSchema = false
)

abstract class MielDataBase : RoomDatabase() {
    abstract fun candidatesDao(): CandidatesDao
}
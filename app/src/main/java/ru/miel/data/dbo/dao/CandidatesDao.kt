package ru.miel.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.miel.data.dbo.entity.CandidatesEntity

@Dao
interface CandidatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listCandidatesEntity: List<CandidatesEntity>): List<Long>

    @Query("DELETE FROM CANDIDATES")
    fun trunc(): Int

    @Query("SELECT * FROM CANDIDATES")
    fun getCandidates(): List<CandidatesEntity>

    @Query("UPDATE CANDIDATES SET isFavorite = 1 WHERE id = :id")
    fun setFavorite(id: Int)

    @Query("UPDATE CANDIDATES SET isFavorite = 0 WHERE id = :id")
    fun delFavorite(id: Int)
}
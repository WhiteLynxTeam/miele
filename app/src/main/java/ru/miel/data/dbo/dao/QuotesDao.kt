package ru.miel.data.dbo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.miel.data.dbo.entity.QuotesEntity
import java.time.LocalDate

@Dao
interface QuotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(listQuotesEntity: List<QuotesEntity>): List<Long>

    @Query("DELETE FROM QUOTES")
    fun trunc(): Int

    @Query("SELECT quotes_remaining FROM QUOTES WHERE :dateMilis BETWEEN start_date AND end_date")
    fun getQuotes(dateMilis: Long): Int

    @Query("UPDATE QUOTES SET quotes_remaining = quotes_remaining - 1 WHERE :dateMilis BETWEEN start_date AND end_date")
    fun minusQuotes(dateMilis: Long)
}
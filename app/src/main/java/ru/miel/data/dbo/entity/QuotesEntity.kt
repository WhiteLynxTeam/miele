package ru.miel.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "QUOTES", primaryKeys = ["start_date", "end_date"])
data class QuotesEntity(
    @field: ColumnInfo(name = "start_date") val start_date: Long,
    @field: ColumnInfo(name = "end_date") val end_date: Long,
    @field: ColumnInfo(name = "quotes") val quotes: Int,
    @field: ColumnInfo(name = "quotes_remaining") val quotes_remaining: Int,
)
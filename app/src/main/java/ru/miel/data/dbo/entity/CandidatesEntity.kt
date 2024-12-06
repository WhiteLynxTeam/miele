package ru.miel.data.dbo.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.miel.domain.models.HasUuid
import java.util.UUID

@Entity(tableName = "CANDIDATES")
data class CandidatesEntity(
    @field: ColumnInfo(name = "id") @field:PrimaryKey(autoGenerate = true) var id: Int = 0,
    @field: ColumnInfo(name = "uuid") override var uuid: UUID? = null,
    @field: ColumnInfo(name = "img") val img: Int = 0,
    @field: ColumnInfo(name = "name") val name: String = "",
    @field: ColumnInfo(name = "year") val year: String = "",
    @field: ColumnInfo(name = "city") val city: String = "",
    @field: ColumnInfo(name = "realtor") val realtor: String = "",
    @field: ColumnInfo(name = "juridicalCourse") val juridicalCourse: String = "",
    @field: ColumnInfo(name = "mortgage") val mortgage: String = "",
    @field: ColumnInfo(name = "taxation") val taxation: String = "",
    @field: ColumnInfo(name = "objects") val objects: String = "",
    @field: ColumnInfo(name = "clients") val clients: String = "",
    @field: ColumnInfo(name = "isInvite") val isInvite: Boolean = false,
    @field: ColumnInfo(name = "isFavorite") val isFavorite: Boolean = false,
) : HasUuid
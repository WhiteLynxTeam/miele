package ru.miel.domain.models

import java.util.UUID

data class Candidates(
    var id: Int? = null,
    var uuid: UUID? = null,
    val img: Int,
    val name: String,
    val year: String,
    val city: String,
    var isFavorite: Boolean,
    val realtor: String,
    val juridicalCourse: String,
    val mortgage: String,
    val taxation: String,
    val objects: String,
    val clients: String,
    var isInvite: Boolean
)

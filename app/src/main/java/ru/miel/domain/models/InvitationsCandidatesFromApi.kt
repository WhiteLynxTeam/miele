package ru.miel.domain.models

import ru.miel.domain.models.enummodel.InvitationStatus

data class InvitationsCandidatesFromApi(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val city: String,
    val age: Int,
    val status: InvitationStatus?,
    val updatedAt: String,
)

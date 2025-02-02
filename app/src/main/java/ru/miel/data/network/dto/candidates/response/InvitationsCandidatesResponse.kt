package ru.miel.data.network.dto.candidates.response

data class InvitationsCandidatesResponse(
    val candidate: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val city: String,
    val age: Int,
    val status: String,
    val updated_at: String,
)
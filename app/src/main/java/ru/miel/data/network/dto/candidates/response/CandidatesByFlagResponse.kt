package ru.miel.data.network.dto.candidates.response

import ru.miel.data.network.dto.candidates.CandidateDto

data class CandidatesByFlagResponse(
    val id: Int,
    val candidate: CandidateDto,
    val created_at: String,
)
package ru.miel.data.dto.candidates.response

import ru.miel.data.dto.candidates.CandidateDto

data class CandidatesByFlagResponse(
    val id: Int,
    val candidate: CandidateDto,
    val created_at: String,
)
package ru.miel.data.network.dto.candidates

data class CandidatesFilterApi(
    val age: Int? = null,
    val age_min: Int? = null,
    val age_max: Int? = null,
    val courses: String,
)

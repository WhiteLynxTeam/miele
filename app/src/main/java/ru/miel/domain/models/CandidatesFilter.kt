package ru.miel.domain.models

data class CandidatesFilter(
    val age: Int? = null,
    val age_min: Int? = null,
    val age_max: Int? = null,
    val course_rieltor_join: Boolean,
    val basic_legal_course: Boolean,
    val course_mortgage: Boolean,
    val course_taxation: Boolean,
)

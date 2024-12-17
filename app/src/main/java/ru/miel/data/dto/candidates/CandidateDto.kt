package ru.miel.data.dto.candidates

data class CandidateDto(
    val id: Int,
    val name: String,
    val surname: String,
    val patronymic: String,
    val birth: String,
    val education: String,
    val photo: String?,
    val country: String,
    val city: String,
    val resume: String,
    val course_rieltor_join: String,
    val basic_legal_course: String,
    val course_mortgage: String,
    val course_taxation: String,
    val completed_objects: Int,
    val clients: Int,
    val updated_at: String,
)

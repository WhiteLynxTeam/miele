package ru.miel.domain.models

data class CandidatesFromApi(
    val id: Int,
//    val is_active: Boolean,
    val name: String?,
    val surname: String?,
    val patronymic: String?,
    val birth: String,
    val age: Int,
    val education: String?,
    val photo: String?,
    val country: String?,
    val city: String?,
//    val email: String,
    val resume: String?,
//    val is_free: Boolean,
    val course_rieltor_join: String,
    val basic_legal_course: String,
    val course_mortgage: String,
    val course_taxation: String,
    val completed_objects: Int,
    val clients: Int,
//    val created_at: String,
    val updated_at: String,
//    val office: String?,
    var isFavorite: Boolean = false,
    val favorite_id: Int?,
    var isInvited: Boolean = false,
)

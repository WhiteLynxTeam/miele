package ru.miel.domain.models

import ru.miel.domain.models.enummodel.CourseStatus

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
    val course_rieltor_join: CourseStatus?,
    val basic_legal_course: CourseStatus?,
    val course_mortgage: CourseStatus?,
    val course_taxation: CourseStatus?,
    val completed_objects: Int,
    val clients: Int,
//    val created_at: String,
    val updated_at: String,
//    val office: String?,
    var isFavorite: Boolean = false,
    val favorite_id: Int?,
    var isInvited: Boolean = false,
)

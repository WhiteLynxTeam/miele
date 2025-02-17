package ru.miel.domain.models

data class CandidatesFilter(
    val age_min: Int? = null,
    val age_max: Int? = null,
    val course_rieltor_join: Boolean,
    val basic_legal_course: Boolean,
    val course_mortgage: Boolean,
    val course_taxation: Boolean,
) {
    fun getSelectedCourses(): String {
        val courses = mutableListOf<String>()

        if (course_rieltor_join) courses.add("course_rieltor_join")
        if (basic_legal_course) courses.add("basic_legal_course")
        if (course_mortgage) courses.add("course_mortgage")
        if (course_taxation) courses.add("course_taxation")

        return courses.joinToString(",")
    }
}

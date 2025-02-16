package ru.miel.domain.models.enummodel

enum class Course(val text: String, val status: CourseStatus) {
    COURSE_RIELTOR_JOIN ("Введение в профессию риелтор", CourseStatus.NOT_STARTED),
    BASIC_LEGAL_COURSE ("Базовый юридический курс", CourseStatus.NOT_STARTED),
    COURSE_MORTGAGE ("Курс “Ипотека”", CourseStatus.NOT_STARTED),
    COURSE_TAXATION ("Курс “Налогообложение”", CourseStatus.NOT_STARTED);

    fun text(): String {
        return text
    }

    companion object {
//        fun fromValue(value: String): Course? {
//            return when (value.lowercase()) {
//                "in_progress" -> IN_PROGRESS
//                "completed" -> COMPLETED
//                "not_started" -> NOT_STARTED
//                else -> null // Если значение не соответствует ни одному из статусов
//            }
//        }
    }
}
package ru.miel.domain.models.enummodel

enum class CourseStatus(val text: String) {
    IN_PROGRESS ("В процессе"),
    COMPLETED ("Пройден"),
    NOT_STARTED ("Не начат");

    fun text(): String {
        return text
    }

    companion object {
        fun fromValue(value: String): CourseStatus? {
            return when (value.lowercase()) {
                "in_progress" -> IN_PROGRESS
                "completed" -> COMPLETED
                "not_started" -> NOT_STARTED
                else -> null // Если значение не соответствует ни одному из статусов
            }
        }
    }
}
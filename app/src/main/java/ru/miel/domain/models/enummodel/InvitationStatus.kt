package ru.miel.domain.models.enummodel

import ru.miel.R

enum class InvitationStatus(val text: String, val colorIdRes: Int) {
    INVITED ("Приглашен", R.color.orange),
    ACCEPTED ("Принят", R.color.turquoise),
    REJECTED ("Отклонен", R.color.bordo);

    fun text(): String {
        return text
    }

    fun color(): Int {
        return colorIdRes
    }

    companion object {
        fun fromValue(value: String): InvitationStatus? {
            return when (value.lowercase()) {
                "invited" -> INVITED
                "accepted" -> ACCEPTED
                "rejected" -> REJECTED
                else -> null // Если значение не соответствует ни одному из статусов
            }
        }
    }
}
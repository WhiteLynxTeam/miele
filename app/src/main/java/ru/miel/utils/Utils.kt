package ru.miel.utils

import ru.miel.domain.models.HasUuid
import java.util.UUID

fun List<HasUuid>.randomUuid() {
    forEach { instance ->
        instance.uuid = UUID.randomUUID()
    }
}

fun String.replaceAfterLastSpace(replacement: String): String {
    val lastSpaceIndex = this.lastIndexOf(' ')

    return if (lastSpaceIndex != -1) {
        this.substring(0, lastSpaceIndex) + " " + replacement
    } else {
        "$this $replacement"
    }
}

fun String.replaceAfterLastSpace(digit: Int): String {
    val lastSpaceIndex = this.lastIndexOf(' ')

    return if (lastSpaceIndex != -1) {
        this.substring(0, lastSpaceIndex) + " $digit"
    } else {
        "$this $digit"
    }
}

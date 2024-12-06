package ru.miel.utils

import ru.miel.domain.models.HasUuid
import java.util.UUID

fun List<HasUuid>.randomUuid() {
    forEach { instance ->
        instance.uuid = UUID.randomUUID()
    }
}

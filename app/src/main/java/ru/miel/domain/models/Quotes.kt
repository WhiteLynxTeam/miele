package ru.miel.domain.models

import java.time.LocalDate

data class Quotes(
    val startDate: LocalDate? = null,
    val endDate: LocalDate? = null,
    val quotesUsed: Int,
    val quotes: Int,
)

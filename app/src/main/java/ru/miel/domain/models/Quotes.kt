package ru.miel.domain.models

import java.time.LocalDate

data class Quotes(
    val startDate: LocalDate?,
    val endDate: LocalDate?,
    val quotes: Int,
    val quotesRemaining: Int,
)

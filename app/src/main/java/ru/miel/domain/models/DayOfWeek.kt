package ru.miel.domain.models

data class Calendar(
    val monday: String,
    val tuesday: String,
    val wednesday: String,
    val thursday: String,
    val friday: String,
    val saturday: String,
    val sunday: String,
    val monthAndYear: String,
    val isSelected: Boolean = false,
    val isToday: Boolean = false
)

package ru.miel.domain.models.enummodel

enum class SortOption(val text: String) {
    NEWEST("Сначала новые"),
    OLDEST("Сначала старые"),
    ALPHABETIC_A_Z("По алфавиту А-Я"),
    ALPHABETIC_Z_A("По алфавиту Я-А");

    fun text(): String {
        return text
    }

    companion object {
        private var selectedPosition: Int = 0

        fun setSelected(pos: Int) {
            selectedPosition =  pos.coerceIn(0, entries.size - 1)
        }

        fun getSelected(): Int {
            return selectedPosition
        }

        fun getSelectedText():String {
            return entries[selectedPosition].text
        }
    }
}
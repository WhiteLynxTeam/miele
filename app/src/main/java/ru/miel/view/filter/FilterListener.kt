package ru.miel.view.filter

import com.google.android.material.textfield.TextInputEditText

interface FilterListener {
    fun onFilterApplied(etAgeFrom: TextInputEditText, etAgeTo: TextInputEditText, selectedCourses: List<String>)
}
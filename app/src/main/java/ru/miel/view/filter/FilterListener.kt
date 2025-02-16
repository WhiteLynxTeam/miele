package ru.miel.view.filter

import ru.miel.domain.models.CandidatesFilter

interface FilterListener {
    fun onFilterApplied(filter: CandidatesFilter)
}
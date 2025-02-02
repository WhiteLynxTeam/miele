package ru.miel.view.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesDbUseCase

class FavoritesViewModel(
    private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    ): ViewModel() {
    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
    val candidates: SharedFlow<List<CandidatesFromApi>>
        get() = _candidates.asSharedFlow()

    fun getFavorites() {
        viewModelScope.launch {
            val result = getCandidatesApiUseCase().filter { it.isFavorite }
            _candidates.emit(result)
        }
    }

    class Factory(
        private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                return FavoritesViewModel(
                    getFavoritesDbUseCase = getFavoritesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
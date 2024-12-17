package ru.miel.view.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.Candidates
import ru.miel.domain.usecase.GetFavoritesDbUseCase

class FavoritesViewModel(
    private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
): ViewModel() {
    private var _candidates = MutableSharedFlow<List<Candidates>>()
    val candidates: SharedFlow<List<Candidates>>
        get() = _candidates.asSharedFlow()

    fun getFavorites() {
        viewModelScope.launch {
            val result = getFavoritesDbUseCase()
            _candidates.emit(result)
        }
    }

    class Factory(
        private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                return FavoritesViewModel(
                    getFavoritesDbUseCase = getFavoritesDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
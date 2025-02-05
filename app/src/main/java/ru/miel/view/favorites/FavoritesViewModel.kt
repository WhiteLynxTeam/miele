package ru.miel.view.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetFavoritesDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationsApiUseCase

class FavoritesViewModel(
    private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    private val setInvitationsApiUseCase: SetInvitationsApiUseCase,
) : ViewModel() {
    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
    val candidates: SharedFlow<List<CandidatesFromApi>>
        get() = _candidates.asSharedFlow()


    private var _isInvite = MutableSharedFlow<Pair<Boolean, Int>>()
    val isInvite: SharedFlow<Pair<Boolean, Int>>
        get() = _isInvite.asSharedFlow()

    fun getFavorites() {
        viewModelScope.launch {
            val result = getCandidatesApiUseCase().filter { it.isFavorite }
            _candidates.emit(result)
        }
    }


    // Обновление статуса приглашения
    fun toggleInvite(id: Int) {
        viewModelScope.launch {
            val result = setInvitationsApiUseCase(id)
            if (result) {
                _isInvite.emit(Pair(result, id))
            }
        }
    }

    class Factory(
        private val getFavoritesDbUseCase: GetFavoritesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
        private val setInvitationsApiUseCase: SetInvitationsApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(FavoritesViewModel::class.java)) {
                return FavoritesViewModel(
                    getFavoritesDbUseCase = getFavoritesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                    setInvitationsApiUseCase = setInvitationsApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
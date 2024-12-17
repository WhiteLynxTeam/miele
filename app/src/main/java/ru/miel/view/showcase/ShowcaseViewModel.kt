package ru.miel.view.showcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.R
import ru.miel.domain.models.Candidates
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesApiUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase
import ru.miel.domain.usecase.SetFavoriteDbUseCase

class ShowcaseViewModel(
    private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
) :
    ViewModel() {
/***Кандидаты с сервера*/
//    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
//    val candidates: SharedFlow<List<CandidatesFromApi>>
//        get() = _candidates.asSharedFlow()
    /****/

    private var _candidates = MutableSharedFlow<List<Candidates>>()
    val candidates: SharedFlow<List<Candidates>>
        get() = _candidates.asSharedFlow()

    private var _isFavorite = MutableSharedFlow<Triple<Boolean, Int, Boolean>>()
    val isFavorite: SharedFlow<Triple<Boolean, Int, Boolean>>
        get() = _isFavorite.asSharedFlow()

    fun getCandidates() {
        viewModelScope.launch {
//            val result = getCandidatesApiUseCase()
            val result = getCandidatesDbUseCase()
            _candidates.emit(result)
        }
    }

    // Обновление состояния избранного
    fun toggleFavorite(id: Int, flag: Boolean) {
//        if (flag) {
//
//        } else {
//
//        }
        viewModelScope.launch {
            _isFavorite.emit(Triple(setFavoriteDbUseCase(id, flag), id, flag))
        }
    }

    // Обновление статуса приглашения
    fun toggleInvite(id: Int, flag: Boolean) {
//        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
//            if (index == position) candidate.copy(isInvite = !candidate.isInvite) else candidate
//        }
    }

    class Factory(
        private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
        private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowcaseViewModel::class.java)) {
                return ShowcaseViewModel(
                    getCandidatesDbUseCase = getCandidatesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                    setFavoriteDbUseCase = setFavoriteDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
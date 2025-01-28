package ru.miel.view.showcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.Candidates
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesDbUseCase
import ru.miel.domain.usecase.candidates.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.candidates.MinusQuoteDbUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationDbUseCase

class ShowcaseViewModel(
    private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
    private val setInvitationDbUseCase: SetInvitationDbUseCase,
    private val getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
    private val minusQuoteDbUseCase: MinusQuoteDbUseCase,
) :
    ViewModel() {
    /***Кандидаты с сервера*/
//    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
//    val candidates: SharedFlow<List<CandidatesFromApi>>
//        get() = _candidates.asSharedFlow()
    /****/

    init {
        getQuotes()
    }

    private var _candidates = MutableSharedFlow<List<Candidates>>()
    val candidates: SharedFlow<List<Candidates>>
        get() = _candidates.asSharedFlow()

    private var _quotes = MutableSharedFlow<Int>()
    val quotes: SharedFlow<Int>
        get() = _quotes.asSharedFlow()

    private var _isFavorite = MutableSharedFlow<Triple<Boolean, Int, Boolean>>()
    val isFavorite: SharedFlow<Triple<Boolean, Int, Boolean>>
        get() = _isFavorite.asSharedFlow()

    private var _isInvite = MutableSharedFlow<Triple<Boolean, Int, Boolean>>()
    val isInvite: SharedFlow<Triple<Boolean, Int, Boolean>>
        get() = _isInvite.asSharedFlow()

    fun getQuotes() {
        viewModelScope.launch {
//            val result = getCandidatesApiUseCase()
            val result = getQuotesByNowDbUseCase()
            _quotes.emit(result)
        }
    }

    fun minusQuote() {
        viewModelScope.launch {
//            val result = getCandidatesApiUseCase()
            if (minusQuoteDbUseCase()) {
                val result = getQuotesByNowDbUseCase()
                _quotes.emit(result)
            }
        }
    }

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
        viewModelScope.launch {
            val quotes = getQuotesByNowDbUseCase()
            if (quotes > 0) {
                if (!flag) {
                    val result = setInvitationDbUseCase(id, flag)
                    if (result) {
                        minusQuote()
                        _isInvite.emit(Triple(result, id, flag))
                    }
                }
            }
        }
    }

    class Factory(
        private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
        private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
        private val setInvitationDbUseCase: SetInvitationDbUseCase,
        private val getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
        private val minusQuoteDbUseCase: MinusQuoteDbUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowcaseViewModel::class.java)) {
                return ShowcaseViewModel(
                    getCandidatesDbUseCase = getCandidatesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                    setFavoriteDbUseCase = setFavoriteDbUseCase,
                    setInvitationDbUseCase = setInvitationDbUseCase,
                    getQuotesByNowDbUseCase = getQuotesByNowDbUseCase,
                    minusQuoteDbUseCase = minusQuoteDbUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
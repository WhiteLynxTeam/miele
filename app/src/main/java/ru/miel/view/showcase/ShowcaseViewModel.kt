package ru.miel.view.showcase

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.CandidatesFromApi
import ru.miel.domain.models.Quotes
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetCandidatesDbUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteApiUseCase
import ru.miel.domain.usecase.candidates.SetFavoriteDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationDbUseCase
import ru.miel.domain.usecase.candidates.SetInvitationsApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesByNowDbUseCase
import ru.miel.domain.usecase.quotes.MinusQuoteDbUseCase

class ShowcaseViewModel(
    private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    private val setFavoriteApiUseCase: SetFavoriteApiUseCase,
    private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
    private val setInvitationDbUseCase: SetInvitationDbUseCase,
    private val getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
    private val minusQuoteDbUseCase: MinusQuoteDbUseCase,
    private val getQuotesApiUseCase: GetQuotesApiUseCase,
    private val setInvitationsApiUseCase: SetInvitationsApiUseCase,
) :
    ViewModel() {
    /***Кандидаты с сервера*/
    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
    val candidates: SharedFlow<List<CandidatesFromApi>>
        get() = _candidates.asSharedFlow()

    /****/

    /*** Red flag. Почему кандидатов вызываем из фрагмента, а квоты - из init вьюмодели
     * разобраться и привести к общему виду*/
    init {
        getQuotes()
    }

    /***Кандидаты с BD, мок-данные*/
//    private var _candidates = MutableSharedFlow<List<Candidates>>()
//    val candidates: SharedFlow<List<Candidates>>
//        get() = _candidates.asSharedFlow()

    private var _quotes = MutableSharedFlow<Quotes>()
    val quotes: SharedFlow<Quotes>
        get() = _quotes.asSharedFlow()

    private var _isFavorite = MutableSharedFlow<Triple<Boolean, Int, Boolean>>()
    val isFavorite: SharedFlow<Triple<Boolean, Int, Boolean>>
        get() = _isFavorite.asSharedFlow()

    private var _isInvite = MutableSharedFlow<Pair<Boolean, Int>>()
    val isInvite: SharedFlow<Pair<Boolean, Int>>
        get() = _isInvite.asSharedFlow()

    fun getQuotes() {
        viewModelScope.launch {
            val result = getQuotesApiUseCase()
//            val result = getQuotesByNowDbUseCase()
            if (result != null) {
                _quotes.emit(result)
            }
        }
    }

    fun minusQuote() {
        viewModelScope.launch {
//            val result = getCandidatesApiUseCase()
            if (minusQuoteDbUseCase()) {
                val result = getQuotesByNowDbUseCase()
//                _quotes.emit(result)
            }
        }
    }

    fun getCandidates() {
        viewModelScope.launch {
            val result = getCandidatesApiUseCase()
//            val result = getCandidatesDbUseCase()
            _candidates.emit(result)
        }
    }

    // Обновление состояния избранного
    fun toggleFavorite(id: Int, flag: Boolean, idFavorite: Int?) {

        viewModelScope.launch {
//            _isFavorite.emit(Triple(setFavoriteDbUseCase(id, flag), id, flag))
            _isFavorite.emit(Triple(setFavoriteApiUseCase(id, flag, idFavorite), id, flag))
//            if (flag) {
//
//            } else {
//
//            }
        }
    }

    // Обновление статуса приглашения
    fun toggleInvite(id: Int) {
        viewModelScope.launch {
            var officeQuotes = getQuotesApiUseCase()
            if (officeQuotes != null && (officeQuotes.quotes - officeQuotes.quotesUsed) > 0) {
                val result = setInvitationsApiUseCase(id)
                if (result) {
                    officeQuotes = getQuotesApiUseCase()
                    if (officeQuotes != null) _quotes.emit(officeQuotes)
                    _isInvite.emit(Pair(result, id))
                }
            }
        }
    }

    class Factory(
        private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
        private val setFavoriteApiUseCase: SetFavoriteApiUseCase,
        private val setFavoriteDbUseCase: SetFavoriteDbUseCase,
        private val setInvitationDbUseCase: SetInvitationDbUseCase,
        private val getQuotesByNowDbUseCase: GetQuotesByNowDbUseCase,
        private val minusQuoteDbUseCase: MinusQuoteDbUseCase,
        private val getQuotesApiUseCase: GetQuotesApiUseCase,
        private val setInvitationsApiUseCase: SetInvitationsApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowcaseViewModel::class.java)) {
                return ShowcaseViewModel(
                    getCandidatesDbUseCase = getCandidatesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                    setFavoriteApiUseCase = setFavoriteApiUseCase,
                    setFavoriteDbUseCase = setFavoriteDbUseCase,
                    setInvitationDbUseCase = setInvitationDbUseCase,
                    getQuotesByNowDbUseCase = getQuotesByNowDbUseCase,
                    minusQuoteDbUseCase = minusQuoteDbUseCase,
                    getQuotesApiUseCase = getQuotesApiUseCase,
                    setInvitationsApiUseCase = setInvitationsApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
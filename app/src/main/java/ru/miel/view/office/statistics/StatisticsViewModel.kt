package ru.miel.view.office.statistics

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.Quotes
import ru.miel.domain.usecase.candidates.GetCountInvitationsApiUseCase
import ru.miel.domain.usecase.quotes.GetQuotesApiUseCase
import ru.miel.domain.usecase.quotes.GetStatisticQuotesApiUseCase

class StatisticsViewModel(
    private val getQuotesApiUseCase: GetQuotesApiUseCase,
    private val getCountInvitationsApiUseCase: GetCountInvitationsApiUseCase,
    private val getStatisticQuotesApiUseCase: GetStatisticQuotesApiUseCase,
) : ViewModel() {

    private var _quotes = MutableSharedFlow<Quotes>()
    val quotes: SharedFlow<Quotes>
        get() = _quotes.asSharedFlow()

    private var _countInvitation = MutableSharedFlow<Int>()
    val countInvitation: SharedFlow<Int>
        get() = _countInvitation.asSharedFlow()

    fun getCountInvitation() {
        viewModelScope.launch {
            _countInvitation.emit(getCountInvitationsApiUseCase())
        }
    }

    fun getQuotes() {
        viewModelScope.launch {
            val result = getQuotesApiUseCase()
            if (result != null) {
                _quotes.emit(result)
            }
        }
    }

    fun getStatisticQuotes() {
        viewModelScope.launch {
            val result = getStatisticQuotesApiUseCase()
            if (result != null) {
                _countInvitation.emit(0)
                _quotes.emit(result)
            }
        }
    }

    class Factory(
        private val getQuotesApiUseCase: GetQuotesApiUseCase,
        private val getCountInvitationsApiUseCase: GetCountInvitationsApiUseCase,
        private val getStatisticQuotesApiUseCase: GetStatisticQuotesApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(StatisticsViewModel::class.java)) {
                return StatisticsViewModel(
                    getQuotesApiUseCase = getQuotesApiUseCase,
                    getCountInvitationsApiUseCase = getCountInvitationsApiUseCase,
                    getStatisticQuotesApiUseCase = getStatisticQuotesApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
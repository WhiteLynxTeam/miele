package ru.miel.view.invitations

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.InvitationsCandidatesFromApi
import ru.miel.domain.usecase.candidates.GetCandidatesApiUseCase
import ru.miel.domain.usecase.candidates.GetInvitationsApiUseCase

class InvitationsViewModel(
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    private val getInvitationsApiUseCase: GetInvitationsApiUseCase,
) : ViewModel() {
    private var _candidates = MutableSharedFlow<List<InvitationsCandidatesFromApi>>()
    val candidates: SharedFlow<List<InvitationsCandidatesFromApi>>
        get() = _candidates.asSharedFlow()

    fun getInvitations() {
        viewModelScope.launch {
            val result = getInvitationsApiUseCase()
            _candidates.emit(result)
        }
    }

    class Factory(
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
        private val getInvitationsApiUseCase: GetInvitationsApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(InvitationsViewModel::class.java)) {
                return InvitationsViewModel(
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                    getInvitationsApiUseCase = getInvitationsApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
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

class ShowcaseViewModel(
    private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
    private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
    private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
) :
    ViewModel() {

    private var _candidates = MutableSharedFlow<List<CandidatesFromApi>>()
    val candidates: SharedFlow<List<CandidatesFromApi>>
        get() = _candidates.asSharedFlow()

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

//    init {
//        fillDb()
//    }

//    fun fillDb() {
//        viewModelScope.launch {
//            val result = filDbWithSampleDataUseCase(
//                listOf(
//                    Candidates(
//                        R.drawable.img_avatar,
//                        "Романова Мария Ивановна",
//                        "22 года",
//                        "Москва",
//                        false,
//                        "Введение в профессию риелтор (пройден)",
//                        "Базовый юридический курс (в процессе)",
//                        "Курс “Ипотека” (в процессе)",
//                        "Курс “Налогообложение” (не начат)",
//                        "Объекты 5",
//                        "Клиенты 5",
//                        false
//                    ),
//                    Candidates(
//                        R.drawable.img_avatar,
//                        "Романова Мария Ивановна",
//                        "22 года",
//                        "Москва",
//                        false,
//                        "Введение в профессию риелтор (пройден)",
//                        "Базовый юридический курс (в процессе)",
//                        "Курс “Ипотека” (в процессе)",
//                        "Курс “Налогообложение” (не начат)",
//                        "Объекты 5",
//                        "Клиенты 5",
//                        false
//                    ),
//                    Candidates(
//                        R.drawable.img_avatar,
//                        "Романова Мария Ивановна",
//                        "22 года",
//                        "Москва",
//                        false,
//                        "Введение в профессию риелтор (пройден)",
//                        "Базовый юридический курс (в процессе)",
//                        "Курс “Ипотека” (в процессе)",
//                        "Курс “Налогообложение” (не начат)",
//                        "Объекты 5",
//                        "Клиенты 5",
//                        false
//                    ),
//                    Candidates(
//                        R.drawable.img_avatar,
//                        "Романова Мария Ивановна",
//                        "22 года",
//                        "Москва",
//                        false,
//                        "Введение в профессию риелтор (пройден)",
//                        "Базовый юридический курс (в процессе)",
//                        "Курс “Ипотека” (в процессе)",
//                        "Курс “Налогообложение” (не начат)",
//                        "Объекты 5",
//                        "Клиенты 5",
//                        false
//                    ),
//                )
//            )
//            _isEntry.emit(result)
//        }
//    }

    fun getCandidates() {
        viewModelScope.launch {
            val result = getCandidatesApiUseCase()
            _candidates.emit(result)
        }
    }

    // Обновление состояния избранного
    fun toggleFavorite(id: Int, flag: Boolean) {
        if (flag) {

        } else {

        }

//        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
//            if (index == position) candidate.copy(isFavorite = !candidate.isFavorite) else candidate
//        }
    }

    // Обновление статуса приглашения
    fun toggleInvite(id: Int, flag: Boolean) {
//        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
//            if (index == position) candidate.copy(isInvite = !candidate.isInvite) else candidate
//        }
    }

    class Factory(
        private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
        private val getCandidatesDbUseCase: GetCandidatesDbUseCase,
        private val getCandidatesApiUseCase: GetCandidatesApiUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowcaseViewModel::class.java)) {
                return ShowcaseViewModel(
                    filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
                    getCandidatesDbUseCase = getCandidatesDbUseCase,
                    getCandidatesApiUseCase = getCandidatesApiUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
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
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase
import ru.miel.domain.usecase.GetCandidatesDbUseCase

class ShowcaseViewModel(
    private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
    private val getCandidatesDbUseCase: GetCandidatesDbUseCase) :
    ViewModel() {

    private var _candidates = MutableSharedFlow<List<Candidates>>()
    val candidates: SharedFlow<List<Candidates>>
        get() = _candidates.asSharedFlow()

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    init {
        fillDb()
    }

    fun fillDb() {
        viewModelScope.launch {
            val result = filDbWithSampleDataUseCase(
                listOf(
                    Candidates(
                        R.drawable.img_avatar,
                        "Романова Мария Ивановна",
                        "22 года",
                        "Москва",
                        false,
                        "Введение в профессию риелтор (пройден)",
                        "Базовый юридический курс (в процессе)",
                        "Курс “Ипотека” (в процессе)",
                        "Курс “Налогообложение” (не начат)",
                        "Объекты 5",
                        "Клиенты 5",
                        false
                    ),
                    Candidates(
                        R.drawable.img_avatar,
                        "Романова Мария Ивановна",
                        "22 года",
                        "Москва",
                        false,
                        "Введение в профессию риелтор (пройден)",
                        "Базовый юридический курс (в процессе)",
                        "Курс “Ипотека” (в процессе)",
                        "Курс “Налогообложение” (не начат)",
                        "Объекты 5",
                        "Клиенты 5",
                        false
                    ),
                    Candidates(
                        R.drawable.img_avatar,
                        "Романова Мария Ивановна",
                        "22 года",
                        "Москва",
                        false,
                        "Введение в профессию риелтор (пройден)",
                        "Базовый юридический курс (в процессе)",
                        "Курс “Ипотека” (в процессе)",
                        "Курс “Налогообложение” (не начат)",
                        "Объекты 5",
                        "Клиенты 5",
                        false
                    ),
                    Candidates(
                        R.drawable.img_avatar,
                        "Романова Мария Ивановна",
                        "22 года",
                        "Москва",
                        false,
                        "Введение в профессию риелтор (пройден)",
                        "Базовый юридический курс (в процессе)",
                        "Курс “Ипотека” (в процессе)",
                        "Курс “Налогообложение” (не начат)",
                        "Объекты 5",
                        "Клиенты 5",
                        false
                    ),
                )
            )
            _isEntry.emit(result)
        }
    }

    fun getCandidates() {
        viewModelScope.launch {
            val result = getCandidatesDbUseCase()
            _candidates.emit(result)
        }
    }

    // Обновление состояния избранного
    fun toggleFavorite(position: Int) {
//        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
//            if (index == position) candidate.copy(isFavorite = !candidate.isFavorite) else candidate
//        }
    }

    // Обновление статуса приглашения
    fun toggleInvite(position: Int) {
//        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
//            if (index == position) candidate.copy(isInvite = !candidate.isInvite) else candidate
//        }
    }

    class Factory(
        private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
        private val getCandidatesDbUseCase: GetCandidatesDbUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShowcaseViewModel::class.java)) {
                return ShowcaseViewModel(
                    filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
                    getCandidatesDbUseCase = getCandidatesDbUseCase
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }

}
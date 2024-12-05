package ru.miel.view.showcase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ru.miel.R
import ru.miel.domain.models.Candidates

class CandidatesViewModel : ViewModel() {

    private val _candidates = MutableLiveData<List<Candidates>>()
    val candidates: LiveData<List<Candidates>> get() = _candidates

    init {
        // Инициализация списка данных
        _candidates.value = generateMockData()
    }

    // Генерация мок-данных
    private fun generateMockData(): List<Candidates> {
        return listOf(
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
                "Пригласить",
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
                "Приглашен",
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
                "Пригласить",
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
                "Пригласить",
                false
            ),
        )
    }

    // Обновление состояния избранного
    fun toggleFavorite(position: Int) {
        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
            if (index == position) candidate.copy(isFavorite = !candidate.isFavorite) else candidate
        }
    }

    // Обновление статуса приглашения
    fun toggleInvite(position: Int) {
        _candidates.value = _candidates.value?.mapIndexed { index, candidate ->
            if (index == position) candidate.copy(isInvite = !candidate.isInvite) else candidate
        }
    }
}

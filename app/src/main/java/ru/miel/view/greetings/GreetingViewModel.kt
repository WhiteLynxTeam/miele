package ru.miel.view.greetings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.usecase.user.GetFullNamePrefUseCase

class GreetingViewModel(
    private val getFullNamePrefUseCase: GetFullNamePrefUseCase,
) : ViewModel() {

    private var _fullName = MutableSharedFlow<String>()
    val fullName: SharedFlow<String>
        get() = _fullName.asSharedFlow()

//    init {
//        getFullName()
//    }

    fun getFullName() {
        viewModelScope.launch {
            val result = getFullNamePrefUseCase()
            if (result != null) {
                _fullName.emit(result)
            }
        }
    }

    class Factory(
        private val getFullNamePrefUseCase: GetFullNamePrefUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(GreetingViewModel::class.java)) {
                return GreetingViewModel(
                    getFullNamePrefUseCase = getFullNamePrefUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
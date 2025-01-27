package ru.miel.view.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.miel.domain.models.User
import ru.miel.domain.sampleListOfCandidates
import ru.miel.domain.usecase.AuthApiUseCase
import ru.miel.domain.usecase.FilDbWithSampleDataUseCase

class AuthViewModel(
    private val authApiUseCase: AuthApiUseCase,
    private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
) : ViewModel() {

    private var _isEntry = MutableSharedFlow<Boolean>()
    val isEntry: SharedFlow<Boolean>
        get() = _isEntry.asSharedFlow()

    private var _isDbCreate = MutableStateFlow<Boolean>(false)
    val isDbCreate: StateFlow<Boolean>
        get() = _isDbCreate.asStateFlow()

    init {
        fillDb()
    }

    fun fillDb() {
        viewModelScope.launch {
            val result = filDbWithSampleDataUseCase()
            _isDbCreate.emit(result)
        }
    }

    fun auth(user: User) {
        viewModelScope.launch {
            if (isDbCreate.value) _isEntry.emit(authApiUseCase(user))
            /*** строка для входа в приложение без сервера и проверки логина, пароля*/
//            if (isDbCreate.value) _isEntry.emit(true)
        }
    }

    class Factory(
        private val authApiUseCase: AuthApiUseCase,
        private val filDbWithSampleDataUseCase: FilDbWithSampleDataUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
                return AuthViewModel(
                    authApiUseCase = authApiUseCase,
                    filDbWithSampleDataUseCase = filDbWithSampleDataUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
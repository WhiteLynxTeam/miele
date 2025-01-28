package ru.miel.view.activity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import ru.miel.domain.usecase.user.GetFullNamePrefUseCase
import ru.miel.domain.usecase.user.GetPhotoUserPrefUseCase

class ActivityMainViewModel(
    private val getFullNamePrefUseCase: GetFullNamePrefUseCase,
    private val getPhotoUserPrefUseCase: GetPhotoUserPrefUseCase,
) : ViewModel() {

    private var _fullName = MutableSharedFlow<String>()
    val fullName: SharedFlow<String>
        get() = _fullName.asSharedFlow()

    private var _photo = MutableSharedFlow<String?>()
    val photo: SharedFlow<String?>
        get() = _photo.asSharedFlow()

    fun getFullNameUser() {
        viewModelScope.launch {
            val result = getFullNamePrefUseCase()
            if (result != null) {
                _fullName.emit(result)
            }
        }
    }

    fun getPhotoUser() {
        viewModelScope.launch {
            val result = getPhotoUserPrefUseCase()
            _photo.emit(result)
        }
    }

    class Factory(
        private val getFullNamePrefUseCase: GetFullNamePrefUseCase,
        private val getPhotoUserPrefUseCase: GetPhotoUserPrefUseCase,
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ActivityMainViewModel::class.java)) {
                return ActivityMainViewModel(
                    getFullNamePrefUseCase = getFullNamePrefUseCase,
                    getPhotoUserPrefUseCase = getPhotoUserPrefUseCase,
                ) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
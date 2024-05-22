package com.example.magicenglish.log_in_screen


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SignInViewModel: ViewModel() {

    //хранение состояния и изменения
    private val _state = MutableStateFlow(SignInState())
    //представляет собой неизменяемое состояние StateFlow
    val state = _state.asStateFlow()

    // функция для обработки результата входа в систему.
    //result: SignInResult - параметр, представляющий результат входа,
    // содержащий данные пользователя или сообщение об ошибке.
    fun onSignInResult(result: SignInResult) {
        //метод copy, создающий новый объект SignInState
        // на основе текущего, с изменёнными значениями полей
        _state.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    //функция для сброса состояния к исходному.
    fun resetState() {
        _state.update { SignInState() }
    }
}
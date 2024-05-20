package com.flexath.findit.auth.presentation.view_models

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.auth.domain.use_cases.RegistrationUseCases
import com.flexath.findit.auth.presentation.events.RegistrationFormEvent
import com.flexath.findit.auth.presentation.events.ValidationEvent
import com.flexath.findit.auth.presentation.states.RegistrationFormState
import com.flexath.findit.core.domain.use_cases.AppEntryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val registrationUseCases: RegistrationUseCases
): ViewModel() {

    private var _authState = mutableStateOf(RegistrationFormState())
    val authState get() = _authState

    private var _validationEvent = Channel<ValidationEvent>()
    val validationEvent get() = _validationEvent.receiveAsFlow()

    fun onRegistrationEvent(event: RegistrationFormEvent) {
        viewModelScope.launch {
            when(event) {
                is RegistrationFormEvent.UserNameChanged -> {
                    _authState.value = authState.value.copy(
                        userName = event.userName
                    )
                }
                is RegistrationFormEvent.EmailChanged -> {
                    _authState.value = authState.value.copy(
                        email = event.email
                    )
                }
                is RegistrationFormEvent.PasswordChanged -> {
                    _authState.value = authState.value.copy(
                        password = event.password
                    )
                }
                is RegistrationFormEvent.RepeatedPasswordChanged -> {
                    _authState.value = authState.value.copy(
                        repeatedPassword = event.repeatedPassword
                    )
                }
                RegistrationFormEvent.Submit -> {
                    submitRegistration()
                }
            }
        }
    }

    private fun submitRegistration() {
        val userNameResult = registrationUseCases.userNameValidation.invoke(_authState.value.userName)
        val emailResult = registrationUseCases.emailValidation.invoke(_authState.value.email)
        val passwordResult = registrationUseCases.passwordValidation.invoke(_authState.value.password)
        val repeatedPasswordResult = registrationUseCases.repeatedPasswordValidation.invoke(_authState.value.password,authState.value.repeatedPassword)

        val hasError = listOf(
            userNameResult,
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any {
            !it.isSuccessful
        }

        if(hasError) {
            viewModelScope.launch {
                _authState.value = authState.value.copy(
                    userNameError = userNameResult.errorMsg,
                    emailError = emailResult.errorMsg,
                    passwordError = passwordResult.errorMsg,
                    repeatedPasswordError = repeatedPasswordResult.errorMsg
                )
            }
            return
        }

        viewModelScope.launch {
            _validationEvent.send(ValidationEvent.Success)
        }
    }
}
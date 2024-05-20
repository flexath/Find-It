package com.flexath.findit.auth.presentation.events

sealed class RegistrationFormEvent {
    data class UserNameChanged(val userName: String) : RegistrationFormEvent()
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()
    data object Submit: RegistrationFormEvent()
}
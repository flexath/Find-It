package com.flexath.auth.presentation.states

data class RegistrationFormState(
    val userName: String = "",
    val userNameError: String? = null,
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null
)

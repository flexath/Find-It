package com.flexath.auth.use_cases

import com.flexath.auth.use_cases.registration.PasswordValidation
import com.flexath.auth.use_cases.registration.RepeatedPasswordValidation
import com.flexath.auth.use_cases.registration.UserNameValidation
import javax.inject.Inject

data class RegistrationUseCases @Inject constructor(
    val userNameValidation: UserNameValidation,
    val emailValidation: com.flexath.auth.use_cases.registration.EmailValidation,
    val passwordValidation: PasswordValidation,
    val repeatedPasswordValidation: RepeatedPasswordValidation
)
package com.flexath.findit.auth.domain.use_cases

import com.flexath.findit.auth.domain.use_cases.registration.EmailValidation
import com.flexath.findit.auth.domain.use_cases.registration.PasswordValidation
import com.flexath.findit.auth.domain.use_cases.registration.RepeatedPasswordValidation
import com.flexath.findit.auth.domain.use_cases.registration.UserNameValidation
import javax.inject.Inject

data class RegistrationUseCases @Inject constructor(
    val userNameValidation: UserNameValidation,
    val emailValidation: EmailValidation,
    val passwordValidation: PasswordValidation,
    val repeatedPasswordValidation: RepeatedPasswordValidation
)
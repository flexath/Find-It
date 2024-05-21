package com.flexath.findit.auth.domain.use_cases.registration

import android.util.Patterns
import com.flexath.findit.auth.domain.use_cases.utils.ValidationResult
import javax.inject.Inject

class RepeatedPasswordValidation @Inject constructor(

) {

    operator fun invoke(
        password: String,
        repeatedPassword: String
    ): ValidationResult {
        if(isEmpty(password,repeatedPassword)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "Repeated Password can't be empty"
            )
        }

        if(!isSamePassword(password,repeatedPassword)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "The passwords don't match"
            )
        }

        return ValidationResult(
            isSuccessful = true
        )
    }

    private fun isEmpty(input: String, repeatedPassword: String): Boolean {
        return input.isBlank() && repeatedPassword.isBlank()
    }

    private fun isSamePassword(input: String, repeatedPassword: String): Boolean {
        return input == repeatedPassword
    }
}
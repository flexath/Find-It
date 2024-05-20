package com.flexath.findit.auth.domain.use_cases.registration

import android.util.Patterns
import com.flexath.findit.auth.domain.use_cases.utils.ValidationResult
import javax.inject.Inject

class EmailValidation @Inject constructor(

) {

    operator fun invoke(
        input: String
    ): ValidationResult {
        if(!isEmpty(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "Email can't be empty"
            )
        }

        if(!isValidEmail(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "That's not a valid email"
            )
        }

        return ValidationResult(
            isSuccessful = true
        )
    }

    private fun isEmpty(input: String): Boolean {
        return input.isNotBlank()
    }

    private fun isValidEmail(input: String): Boolean {
        return Patterns.EMAIL_ADDRESS.matcher(input).matches()
    }
}
package com.flexath.findit.auth.domain.use_cases.registration

import android.util.Patterns
import com.flexath.findit.auth.domain.use_cases.utils.ValidationResult
import javax.inject.Inject

class PasswordValidation @Inject constructor(

) {

    operator fun invoke(
        input: String
    ): ValidationResult {
        if(isEmpty(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "Password can't be empty"
            )
        }

        if(isLessThan8(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "The password needs to consist of al least 8 characters"
            )
        }

        if(!containsAtLeastOneLetterAndDigit(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "The password needs to consist of at least one letter and one number"
            )
        }

        return ValidationResult(
            isSuccessful = true
        )
    }

    private fun isEmpty(input: String): Boolean {
        return input.isBlank()
    }

    private fun isLessThan8(input: String): Boolean {
        return input.length < 8
    }

    private fun containsAtLeastOneLetterAndDigit(input: String): Boolean {
        return input.any {
            it.isDigit()
        } && input.any {
            it.isLetter()
        }
    }
}
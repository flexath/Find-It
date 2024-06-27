package com.flexath.auth.use_cases.registration

import com.flexath.auth.use_cases.utils.ValidationResult
import javax.inject.Inject

class UserNameValidation @Inject constructor() {
    operator fun invoke(
        input: String
    ): ValidationResult {
        if(!isEmpty(input)) {
            return ValidationResult(
                isSuccessful = false,
                errorMsg = "User Name can't be empty"
            )
        }
        return ValidationResult(
            isSuccessful = true
        )
    }

    private fun isEmpty(input: String): Boolean {
        return input.isNotBlank()
    }
}
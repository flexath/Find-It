package com.flexath.findit.auth.presentation.events

import com.flexath.findit.auth.domain.use_cases.utils.ValidationResult

sealed class ValidationEvent {
    data object Success: ValidationEvent()
}
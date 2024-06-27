package com.flexath.auth.presentation.events

sealed class ValidationEvent {
    data object Success: ValidationEvent()
}
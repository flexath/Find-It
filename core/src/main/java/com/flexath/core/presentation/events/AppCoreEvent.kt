package com.flexath.core.presentation.events

sealed class AppCoreEvent {
    data object AuthEvent : AppCoreEvent()
}
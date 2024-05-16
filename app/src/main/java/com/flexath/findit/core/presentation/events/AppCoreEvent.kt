package com.flexath.findit.core.presentation.events

sealed class AppCoreEvent() {
    data object AuthEvent : AppCoreEvent()
}
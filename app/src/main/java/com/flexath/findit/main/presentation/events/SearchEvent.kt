package com.flexath.findit.main.presentation.events

sealed class SearchEvent {
    class UpdateQuery(val query: String) : SearchEvent()
    data object Search : SearchEvent()
}
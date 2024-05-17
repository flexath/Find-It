package com.flexath.findit.core.presentation.view_model

import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.domain.use_cases.AppEntryUseCase
import com.flexath.findit.core.presentation.Route
import com.flexath.findit.core.presentation.events.AppCoreEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
): ViewModel() {

    private var _startDestination = mutableStateOf(Route.AuthSubGraph.route)
    val startDestination get() = _startDestination

    init {
        getStartDestination()
    }

    private fun getStartDestination() {
        appEntryUseCase.readAppEntry.invoke()
            .flowOn(Dispatchers.IO)
            .onEach {
                _startDestination.value = if(it) {
                    Route.MainSubGraph.route
                } else {
                    Route.AuthSubGraph.route
                }
            }.launchIn(viewModelScope)
    }

    fun onEvent(event: AppCoreEvent) {
        when(event) {
            is AppCoreEvent.AuthEvent -> {
                saveAppEntry()
            }
        }
    }

    private fun saveAppEntry() {
        viewModelScope.launch {
            appEntryUseCase.saveAppEntry.invoke()
        }
    }
}
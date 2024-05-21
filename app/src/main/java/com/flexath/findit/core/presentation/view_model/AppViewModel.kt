package com.flexath.findit.core.presentation.view_model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.domain.use_cases.AppEntryUseCase
import com.flexath.findit.core.presentation.Route
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AppViewModel @Inject constructor(
    private val appEntryUseCase: AppEntryUseCase
): ViewModel() {

    var startDestination by mutableStateOf("")
        private set

    init {
        getStartDestination()
    }

    private fun getStartDestination() {
        appEntryUseCase.readAppEntry.invoke()
            .onEach {
                startDestination = if(it) {
                    Route.MainSubGraph.route
                } else {
                    Route.AuthSubGraph.route
                }
            }.launchIn(viewModelScope)
    }
}
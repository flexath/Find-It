package com.flexath.findit.main.presentation.view_model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.use_cases.MainUseCase
import com.flexath.findit.main.presentation.events.SearchEvent
import com.flexath.findit.main.presentation.states.ProductHistoryState
import com.flexath.findit.main.presentation.states.ProductSearchState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private var _productSearchState = mutableStateOf(ProductSearchState())
    val productSearchState: State<ProductSearchState> = _productSearchState

    private var _productSearchHistoryState = mutableStateOf(ProductHistoryState())
    val productSearchHistoryState: State<ProductHistoryState> = _productSearchHistoryState

    init {
        getAllSearchHistory()
    }

    fun onProductEvent(event: SearchEvent) {
        when(event) {
            is SearchEvent.UpdateQuery -> {
                _productSearchState.value = productSearchState.value.copy(
                    query = event.query
                )
            }
            is SearchEvent.Search -> {
                _productSearchState.value = productSearchState.value.copy(
                    productList = emptyList()
                )
                searchProducts(productSearchState.value.query)
            }
        }
    }

    private fun searchProducts(query: String) {
        viewModelScope.launch {
            mainUseCase.searchProductsUseCase.invoke(query = query)
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productSearchState.value = productSearchState.value.copy(
                                productList = emptyList()
                            )
                        }

                        is Resource.Success -> {
                            _productSearchState.value = productSearchState.value.copy(
                                productList = it.data ?: emptyList(),
                            )
                        }

                        else -> {
                            _productSearchState.value = productSearchState.value.copy(
                                productList = emptyList()
                            )
                        }
                    }
                }
        }
    }

    fun insertSearchHistory(query: String) {
        viewModelScope.launch {
            mainUseCase.insertHistoryUseCase.invoke(query)
            getAllSearchHistory()
        }
    }

    fun deleteSearchHistory(id: Int) {
        viewModelScope.launch {
            mainUseCase.deleteHistoryUseCase.invoke(id)
        }

        getAllSearchHistory()
    }

    private fun getAllSearchHistory() {
        viewModelScope.launch {
            mainUseCase.getAllSearchHistoryUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productSearchHistoryState.value = productSearchHistoryState.value.copy(
                                searchHistoryList = emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _productSearchHistoryState.value = productSearchHistoryState.value.copy(
                                searchHistoryList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            _productSearchHistoryState.value = productSearchHistoryState.value.copy(
                                searchHistoryList = emptyList(),
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }
}
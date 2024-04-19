package com.flexath.findit.main.presentation.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.use_cases.MainUseCase
import com.flexath.findit.main.presentation.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val productUseCase: MainUseCase
) : ViewModel() {

    private val _productListState = mutableStateOf(ProductListState())
    val productListState: State<ProductListState> = _productListState

    init {
        fetchFeatureProducts()
    }

    private fun fetchFeatureProducts() {
        viewModelScope.launch {
            productUseCase.productUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect {
                    Log.i("CollectData",it.data.toString())

                    when (it) {
                        is Resource.Loading -> {
                            Log.i("CollectData Resource","Loading")
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            Log.i("CollectData Resource","Success")
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            Log.i("CollectData Resource","Error")
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}
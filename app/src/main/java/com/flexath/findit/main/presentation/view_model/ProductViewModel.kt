package com.flexath.findit.main.presentation.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.use_cases.MainUseCase
import com.flexath.findit.main.presentation.state.ProductCategoryListState
import com.flexath.findit.main.presentation.state.ProductListState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private val _productListState = mutableStateOf(ProductListState())
    val productListState: State<ProductListState> = _productListState

    private val _productCategoryListState = mutableStateOf(ProductCategoryListState())
    val productCategoryListState: State<ProductCategoryListState> = _productCategoryListState

    // Shared flow to emit product data
    private val _productSharedFlow = MutableSharedFlow<ProductVO>(replay = 0)
    val productSharedFlow = _productSharedFlow.asSharedFlow()

    // Shared flow to emit product data
    private val _productListSharedFlow = MutableSharedFlow<List<ProductVO>>(replay = 0)
    val productListSharedFlow = _productListSharedFlow.asSharedFlow()

    fun fetchAllProducts() {
        viewModelScope.launch {
            mainUseCase.allProductsUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            _productListState.value = productListState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }

    fun fetchProduct(
        productId: Int
    ) {
        viewModelScope.launch {
            mainUseCase.productUseCase.invoke(productId)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {

                        }

                        is Resource.Success -> {
                            it.data?.let { product ->
                                _productSharedFlow.emit(product)
                            }

                            _productListSharedFlow.emit(productListState.value.productList)
                        }

                        else -> {

                        }
                    }
                }
        }
    }

    fun fetchAllProductCategories() {
        viewModelScope.launch {
            mainUseCase.allProductCategoriesUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("CollectData Resource", "Loading")
                            _productCategoryListState.value = productCategoryListState.value.copy(
                                productCategoryList = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _productCategoryListState.value = productCategoryListState.value.copy(
                                productCategoryList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            _productCategoryListState.value = productCategoryListState.value.copy(
                                productCategoryList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}
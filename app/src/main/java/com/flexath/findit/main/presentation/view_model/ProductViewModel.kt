package com.flexath.findit.main.presentation.view_model

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.use_cases.MainUseCase
import com.flexath.findit.main.presentation.states.ProductCategoryListState
import com.flexath.findit.main.presentation.states.ProductListState
import com.flexath.findit.main.presentation.states.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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
    private val _productState = mutableStateOf(ProductState())
    val productState = _productState

    // Shared flow to emit product data
    private val _productListSharedFlow = MutableSharedFlow<List<ProductVO>>(replay = 0)
    val productListSharedFlow = _productListSharedFlow.asSharedFlow()

    private val _productListOfCategoryState = mutableStateOf(ProductListState())
    val productListOfCategoryState: State<ProductListState> = _productListOfCategoryState

    fun fetchAllProducts() {
        viewModelScope.launch {
            mainUseCase.getAllProductsUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
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
            mainUseCase.getProductUseCase.invoke(productId)
                .flowOn(Dispatchers.IO)
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productState.value = productState.value.copy(
                                product = it.data,
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _productState.value = productState.value.copy(
                                product = it.data,
                                isLoading = true
                            )
                        }

                        else -> {
                            _productState.value = productState.value.copy(
                                product = it.data,
                                isLoading = true
                            )
                        }
                    }
                }
        }
    }

    fun fetchAllProductCategories() {
        viewModelScope.launch {
            mainUseCase.getAllProductCategoriesUseCase.invoke()
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

    fun fetchAllProductsOfCategory(
        categoryName: String
    ) {

        viewModelScope.launch {
            mainUseCase.getAllProductOfCategoryUseCase.invoke(categoryName = categoryName)
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productListOfCategoryState.value = productListOfCategoryState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = true
                            )
                        }

                        is Resource.Success -> {
                            _productListOfCategoryState.value = productListOfCategoryState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }

                        else -> {
                            _productListOfCategoryState.value = productListOfCategoryState.value.copy(
                                productList = it.data ?: emptyList(),
                                isLoading = false
                            )
                        }
                    }
                }
        }
    }
}
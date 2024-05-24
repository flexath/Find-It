package com.flexath.findit.main.presentation.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.use_cases.MainUseCase
import com.flexath.findit.main.presentation.states.ProductCategoryListState
import com.flexath.findit.main.presentation.states.ProductListState
import com.flexath.findit.main.presentation.states.ProductState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val mainUseCase: MainUseCase
) : ViewModel() {

    private var _productListState = MutableStateFlow(ProductListState())
    val productListState get() = _productListState.asStateFlow()

    private val _isProductListFetched = MutableStateFlow(false)
    val isProductListFetched get() = _isProductListFetched.asStateFlow()

    private val _productCategoryListState = MutableStateFlow(ProductCategoryListState())
    val productCategoryListState get() = _productCategoryListState.asStateFlow()

    // Shared flow to emit product data
    private val _productState = MutableStateFlow(ProductState())
    val productState get() = _productState.asStateFlow()

    private val _productListOfCategoryState = MutableStateFlow(ProductListState())
    val productListOfCategoryState get() = _productListOfCategoryState.asStateFlow()

    fun fetchAllProducts() {
        viewModelScope.launch {
            mainUseCase.getAllProductsUseCase.invoke()
                .flowOn(Dispatchers.IO)
                .distinctUntilChanged()
                .collect {
                    when (it) {
                        is Resource.Loading -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }

                        else -> {
                            _productListState.update { productState ->
                                productState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }
                    }
                    _isProductListFetched.update {
                        true
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
                            _productState.update { product ->
                                product.copy(
                                    product = it.data,
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productState.update { product ->
                                product.copy(
                                    product = it.data,
                                    isLoading = true
                                )
                            }
                        }

                        else -> {
                            _productState.update { product ->
                                product.copy(
                                    product = it.data,
                                    isLoading = true
                                )
                            }
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
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
                        }

                        else -> {
                            _productCategoryListState.update { productCategoryState ->
                                productCategoryState.copy(
                                    productCategoryList = it.data ?: emptyList(),
                                    isLoading = false
                                )
                            }
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
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        is Resource.Success -> {
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = it.data ?: emptyList(),
                                    isLoading = true
                                )
                            }
                        }

                        else -> {
                            _productListOfCategoryState.update { categoryProductListState ->
                                categoryProductListState.copy(
                                    productList = emptyList(),
                                    isLoading = true
                                )
                            }
                        }
                    }
                }
        }
    }
}
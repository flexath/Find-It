package com.flexath.findit.main.presentation.state

data class ProductCategoryListState(
    val productCategoryList: List<String> = emptyList(),
    val isLoading: Boolean = false
)

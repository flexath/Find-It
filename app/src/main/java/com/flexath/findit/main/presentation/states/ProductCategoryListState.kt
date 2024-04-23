package com.flexath.findit.main.presentation.states

data class ProductCategoryListState(
    val productCategoryList: List<String> = emptyList(),
    val isLoading: Boolean = false
)

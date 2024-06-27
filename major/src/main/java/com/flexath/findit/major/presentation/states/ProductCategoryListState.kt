package com.flexath.findit.major.presentation.states

data class ProductCategoryListState(
    val productCategoryList: List<String> = emptyList(),
    val isLoading: Boolean = false
)

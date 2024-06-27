package com.flexath.findit.major.presentation.states

import com.flexath.findit.major.domain.model.ProductVO

data class ProductListState(
    val productList: List<com.flexath.findit.major.domain.model.ProductVO> = emptyList(),
    val isLoading: Boolean = false
)

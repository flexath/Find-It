package com.flexath.findit.main.presentation.state

import com.flexath.findit.main.domain.model.ProductVO

data class ProductListState(
    val productList: List<ProductVO> = emptyList(),
    val isLoading: Boolean = false
)

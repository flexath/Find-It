package com.flexath.findit.main.presentation.state

import com.flexath.findit.main.domain.model.ProductVO

data class ProductState(
    val product: ProductVO? = null,
    val isLoading: Boolean = false
)

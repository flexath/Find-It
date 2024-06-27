package com.flexath.findit.major.presentation.states

import com.flexath.findit.major.domain.model.ProductVO

data class ProductState(
    val product: com.flexath.findit.major.domain.model.ProductVO? = null,
    val isLoading: Boolean = false
)

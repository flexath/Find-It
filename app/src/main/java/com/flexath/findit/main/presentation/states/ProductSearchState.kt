package com.flexath.findit.main.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.main.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

data class ProductSearchState(
    val query: String = "",
    val productList: List<ProductVO> = emptyList()
)
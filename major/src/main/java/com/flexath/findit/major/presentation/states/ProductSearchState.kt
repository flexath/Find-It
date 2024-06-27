package com.flexath.findit.major.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.major.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

data class ProductSearchState(
    val query: String = "",
    val productList: List<com.flexath.findit.major.domain.model.ProductVO> = emptyList()
)
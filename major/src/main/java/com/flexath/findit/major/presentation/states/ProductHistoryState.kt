package com.flexath.findit.major.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.major.domain.model.HistoryVO
import com.flexath.findit.major.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

data class ProductHistoryState(
    val searchHistoryList: List<com.flexath.findit.major.domain.model.HistoryVO> = emptyList(),
    val isLoading: Boolean = false
)
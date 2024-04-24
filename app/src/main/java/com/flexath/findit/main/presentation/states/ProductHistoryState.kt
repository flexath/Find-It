package com.flexath.findit.main.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

data class ProductHistoryState(
    val searchHistoryList: List<HistoryVO> = emptyList(),
    val isLoading: Boolean = false
)
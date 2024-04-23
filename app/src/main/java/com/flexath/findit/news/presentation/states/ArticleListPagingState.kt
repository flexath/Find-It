package com.flexath.findit.news.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ArticleListPagingState(
    val articleList: Flow<PagingData<ArticleVO>> = flowOf(),
    val isLoading: Boolean = false
)
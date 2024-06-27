package com.flexath.news.presentation.states

import androidx.paging.PagingData
import com.flexath.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

data class ArticleListPagingState(
    val articleList: Flow<PagingData<ArticleVO>> = flowOf(),
    val isLoading: Boolean = false
)
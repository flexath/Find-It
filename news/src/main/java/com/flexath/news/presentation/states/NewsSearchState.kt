package com.flexath.news.presentation.states

import androidx.paging.PagingData
import com.flexath.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow

data class NewsSearchState(
    val query: String = "",
    var articles: Flow<PagingData<ArticleVO>>? = null
)

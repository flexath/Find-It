package com.flexath.findit.news.presentation.states

import androidx.paging.PagingData
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow

data class NewsSearchState(
    val query: String = "",
    var articles: Flow<PagingData<ArticleVO>>? = null
)

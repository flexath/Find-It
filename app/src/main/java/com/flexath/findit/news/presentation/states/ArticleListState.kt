package com.flexath.findit.news.presentation.states

import com.flexath.findit.news.domain.model.ArticleVO

data class ArticleListState(
    val articleList: List<ArticleVO> = emptyList(),
    val isLoading: Boolean = false
)

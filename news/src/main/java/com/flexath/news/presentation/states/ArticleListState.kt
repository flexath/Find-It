package com.flexath.news.presentation.states

import com.flexath.news.domain.model.ArticleVO

data class ArticleListState(
    val articleList: List<ArticleVO> = emptyList(),
    val isLoading: Boolean = false
)

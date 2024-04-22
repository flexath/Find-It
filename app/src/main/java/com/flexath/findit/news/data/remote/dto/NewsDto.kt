package com.flexath.findit.news.data.remote.dto

import com.flexath.findit.news.domain.model.ArticleVO

data class NewsDto(
    val articles: List<ArticleVO>,
    val status: String,
    val totalResults: Int
)
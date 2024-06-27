package com.flexath.news.data.remote.dto

import com.flexath.news.domain.model.ArticleVO
import com.google.gson.annotations.SerializedName

data class NewsDto(
    @SerializedName("articles")
    val articles: List<ArticleVO>?,
    @SerializedName("status")
    val status: String?,
    @SerializedName("totalResults")
    val totalResults: Int?
)
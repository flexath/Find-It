package com.flexath.findit.news.domain.repository

import androidx.paging.PagingData
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(sources: List<String>) : Flow<PagingData<ArticleVO>>

    fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticleVO>>

    suspend fun insertArticles(articleList: List<ArticleVO>)

    fun getArticles() : Flow<List<ArticleVO>>
}
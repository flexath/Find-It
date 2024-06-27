package com.flexath.news.domain.repository

import androidx.paging.PagingData
import com.flexath.core.utils.Resource
import com.flexath.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsForHomeScreen(): Flow<Resource<List<ArticleVO>>>
    fun getNews(query: String) : Flow<PagingData<ArticleVO>>
}
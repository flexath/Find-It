package com.flexath.findit.news.domain.repository

import androidx.paging.PagingData
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNewsForHomeScreen(): Flow<Resource<List<ArticleVO>>>
    fun getNews(query: String) : Flow<PagingData<ArticleVO>>
}
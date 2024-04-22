package com.flexath.findit.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.flexath.findit.news.data.local.NewsDao
import com.flexath.findit.news.data.remote.NewsPagingSource
import com.flexath.findit.news.data.remote.SearchNewsPagingSource
import com.flexath.findit.news.data.remote.api.NewsApi
import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val dao: NewsDao
) : NewsRepository {
    override fun getNews(sources: List<String>): Flow<PagingData<ArticleVO>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                initialLoadSize = 10,
                enablePlaceholders = true,
                prefetchDistance = 3
            ),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override fun searchNews(searchQuery: String, sources: List<String>): Flow<PagingData<ArticleVO>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchNewsPagingSource(
                    api = newsApi,
                    searchQuery = searchQuery,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

    override suspend fun insertArticles(articleList: List<ArticleVO>) {
        dao.insertArticles(articleList = articleList)
    }

    override fun getArticles(): Flow<List<ArticleVO>> = flow {

    }
}
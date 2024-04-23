package com.flexath.findit.news.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.news.data.local.NewsDatabase
import com.flexath.findit.news.data.remote.NewsPagingSource
import com.flexath.findit.news.data.remote.api.NewsApi
import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi,
    private val newsDatabase: NewsDatabase
) : NewsRepository {

    override fun getNewsForHomeScreen(): Flow<Resource<List<ArticleVO>>> = flow {
        emit(Resource.Loading())

        try {
            val articleList = newsApi.getNews(page = 1).articles
            newsDatabase.newsDao.deleteArticleList()
            newsDatabase.newsDao.insertArticles(articleList.orEmpty())
        } catch (e: HttpException) {
            emit(
                Resource.Error(
                    message = "Oops, something went wrong!",
                    data = listOf()
                )
            )
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    message = "Couldn't reach server, check your internet connection.",
                    data = listOf()
                )
            )
        }

        emit(
            Resource.Success(
                data = newsDatabase.newsDao.getArticles()
            )
        )
    }

    override fun getNews(
        query: String
    ): Flow<PagingData<ArticleVO>> {
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
                    query = query
                )
            }
        ).flow
    }
}
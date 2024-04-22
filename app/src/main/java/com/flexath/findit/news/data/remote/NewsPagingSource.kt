package com.flexath.findit.news.data.remote

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flexath.findit.news.data.remote.api.NewsApi
import com.flexath.findit.news.domain.model.ArticleVO

class NewsPagingSource(
    val newsApi: NewsApi,
    val sources: String
) : PagingSource<Int, ArticleVO>() {

    private var totalPageCount  = 0

    override fun getRefreshKey(state: PagingState<Int, ArticleVO>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ArticleVO> {
        val page = params.key ?: 1
        return try {
            val newResponse = newsApi.getNews(
                page = page,
                sources = sources
            )
            totalPageCount += newResponse.articles.size
            val articles = newResponse.articles.distinctBy { it.title }

            LoadResult.Page(
                data = articles,
                prevKey = if (page == 1) null else -1,
                nextKey = if(totalPageCount == newResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}
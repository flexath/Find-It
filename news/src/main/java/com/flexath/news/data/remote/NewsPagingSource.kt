package com.flexath.news.data.remote

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.flexath.news.data.remote.api.NewsApi
import com.flexath.news.domain.model.ArticleVO
import javax.inject.Inject

class NewsPagingSource @Inject constructor(
    val newsApi: NewsApi,
    val query:String
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

        Log.i("NewsPage","Page $page")

        return try {
            val newResponse = newsApi.getNews(
                searchQuery = query,
                page = page
            )
            totalPageCount += newResponse.articles?.size ?: 0
            val articles = newResponse.articles?.distinctBy { it.title }

            LoadResult.Page(
                data = articles ?: emptyList(),
                prevKey = if (page == 1) null else -1,
                nextKey = if(totalPageCount == newResponse.totalResults) null else page + 1
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(throwable = e)
        }
    }

}
package com.flexath.findit.news.data.remote.api

import com.flexath.findit.core.data.ApiConstants.NEWS_API_KEY
import com.flexath.findit.core.data.ApiConstants.PARAM_API_KEY
import com.flexath.findit.core.data.ApiConstants.PARAM_PAGE
import com.flexath.findit.core.data.ApiConstants.PARAM_SOURCES
import com.flexath.findit.news.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_SOURCES) sources: String,
        @Query(PARAM_API_KEY) apiKey: String = NEWS_API_KEY
    ): NewsDto

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery: String,
        @Query("sources") sources: String,
        @Query("page") page: Int,
        @Query("apiKey") apiKey: String = NEWS_API_KEY
    ): NewsDto
}
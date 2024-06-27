package com.flexath.news.data.remote.api

import com.flexath.core.data.ApiConstants.NEWS_API_KEY
import com.flexath.core.data.ApiConstants.PARAM_API_KEY
import com.flexath.core.data.ApiConstants.PARAM_PAGE
import com.flexath.news.data.remote.dto.NewsDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("q") searchQuery: String = "shopping",
        @Query("pageSize") pageSize: Int = 10,
        @Query(PARAM_PAGE) page: Int,
        @Query(PARAM_API_KEY) apiKey: String = NEWS_API_KEY
    ): NewsDto
}
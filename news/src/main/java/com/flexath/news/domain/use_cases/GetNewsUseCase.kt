package com.flexath.news.domain.use_cases

import androidx.paging.PagingData
import com.flexath.news.domain.model.ArticleVO
import com.flexath.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String): Flow<PagingData<ArticleVO>> {
        return repository.getNews(query = query)
    }
}
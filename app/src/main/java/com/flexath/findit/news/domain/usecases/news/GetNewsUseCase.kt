package com.flexath.findit.news.domain.usecases.news

import androidx.paging.PagingData
import com.flexath.findit.news.domain.repository.NewsRepository
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(query: String): Flow<PagingData<ArticleVO>> {
        return repository.getNews(query = query)
    }
}
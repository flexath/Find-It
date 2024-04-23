package com.flexath.findit.news.domain.usecases.news

import androidx.paging.PagingData
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.news.domain.repository.NewsRepository
import com.flexath.findit.news.domain.model.ArticleVO
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsForHomeScreenUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<ArticleVO>>> {
        return repository.getNewsForHomeScreen()
    }
}
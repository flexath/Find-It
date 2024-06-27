package com.flexath.news.domain.use_cases

import com.flexath.core.utils.Resource
import com.flexath.news.domain.model.ArticleVO
import com.flexath.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNewsForHomeScreenUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<Resource<List<ArticleVO>>> {
        return repository.getNewsForHomeScreen()
    }
}
package com.flexath.findit.news.domain.usecases.news

import com.flexath.findit.news.domain.model.ArticleVO
import com.flexath.findit.news.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    operator fun invoke(): Flow<List<ArticleVO>> = repository.getArticles()
}
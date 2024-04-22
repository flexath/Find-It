package com.flexath.findit.news.domain.usecases.news

import com.flexath.findit.news.domain.repository.NewsRepository
import com.flexath.findit.news.domain.model.ArticleVO
import javax.inject.Inject

class DeleteArticleUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(article: ArticleVO) {
        repository.deleteArticle(article = article)
    }
}
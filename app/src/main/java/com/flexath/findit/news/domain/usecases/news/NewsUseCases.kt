package com.flexath.findit.news.domain.usecases.news

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNewsUseCases: GetNewsUseCase,
    val searchNews: SearchNewsUseCase,
    val insertArticle: InsertArticleUseCase,
    val deleteArticle: DeleteArticleUseCase,
    val getArticles: GetArticlesUseCase,
    val getArticle: GetArticleUseCase
)
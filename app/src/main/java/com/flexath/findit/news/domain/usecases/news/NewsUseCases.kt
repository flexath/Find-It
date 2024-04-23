package com.flexath.findit.news.domain.usecases.news

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNewsUseCases: GetNewsUseCase,
    val getNewsForHomeScreenUseCase: GetNewsForHomeScreenUseCase
)
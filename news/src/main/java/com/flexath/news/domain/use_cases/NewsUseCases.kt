package com.flexath.news.domain.use_cases

import javax.inject.Inject

data class NewsUseCases @Inject constructor(
    val getNewsUseCases: GetNewsUseCase,
    val getNewsForHomeScreenUseCase: GetNewsForHomeScreenUseCase
)
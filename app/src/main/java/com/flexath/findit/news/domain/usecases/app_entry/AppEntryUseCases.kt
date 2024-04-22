package com.flexath.findit.news.domain.usecases.app_entry

import javax.inject.Inject

data class AppEntryUseCases @Inject constructor(
    val readAppEntry: ReadAppEntryUseCase,
    val saveAppEntry: SaveAppEntryUseCase
)

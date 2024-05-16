package com.flexath.findit.core.domain.use_cases

import javax.inject.Inject

data class AppEntryUseCase @Inject constructor(
    val saveAppEntry: SaveAppEntryCase,
    val readAppEntry: ReadAppEntryUsecase
)
package com.flexath.findit.main.domain.use_cases

import javax.inject.Inject

data class MainUseCase @Inject constructor(
    val productUseCase: GetAllProductUseCase
)

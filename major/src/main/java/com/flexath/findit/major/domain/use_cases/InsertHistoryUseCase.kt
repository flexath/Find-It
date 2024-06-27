package com.flexath.findit.major.domain.use_cases

import com.flexath.findit.major.domain.repository.ProductRepository
import javax.inject.Inject

class InsertHistoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(query: String) {
        productRepository.insertSearchHistory(query = query)
    }
}
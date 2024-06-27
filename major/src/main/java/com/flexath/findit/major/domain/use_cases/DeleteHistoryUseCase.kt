package com.flexath.findit.major.domain.use_cases

import com.flexath.findit.major.domain.repository.ProductRepository
import javax.inject.Inject

class DeleteHistoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int) {
        productRepository.deleteSearchHistory(id = id)
    }
}
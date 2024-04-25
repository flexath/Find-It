package com.flexath.findit.main.domain.use_cases

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DeleteHistoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    suspend operator fun invoke(id: Int) {
        productRepository.deleteSearchHistory(id = id)
    }
}
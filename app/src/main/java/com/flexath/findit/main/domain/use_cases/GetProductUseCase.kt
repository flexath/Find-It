package com.flexath.findit.main.domain.use_cases

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        productId: Int
    ): Flow<Resource<ProductVO>> {
        return productRepository.getProduct(productId)
    }
}
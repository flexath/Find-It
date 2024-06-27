package com.flexath.findit.major.domain.use_cases

import com.flexath.core.utils.Resource
import com.flexath.findit.major.domain.model.ProductVO
import com.flexath.findit.major.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<ProductVO>>> {
        return productRepository.searchProducts(query = query)
    }
}
package com.flexath.findit.main.domain.use_cases

import androidx.paging.PagingData
import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchProductsUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(query: String): Flow<Resource<List<ProductVO>>> {
        return productRepository.searchProducts(query = query)
    }
}
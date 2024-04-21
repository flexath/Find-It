package com.flexath.findit.main.domain.use_cases

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllProductOfCategoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(
        categoryName: String
    ): Flow<Resource<List<ProductVO>>> {
        return productRepository.getProductsOfCategory(categoryName)
    }
}
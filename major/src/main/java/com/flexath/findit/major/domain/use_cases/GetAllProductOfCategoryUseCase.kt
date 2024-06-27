package com.flexath.findit.major.domain.use_cases

import com.flexath.core.utils.Resource
import com.flexath.findit.major.domain.model.ProductVO
import com.flexath.findit.major.domain.repository.ProductRepository
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
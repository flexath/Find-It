package com.flexath.findit.major.domain.use_cases

import com.flexath.core.utils.Resource
import com.flexath.findit.major.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllCategoriesUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<String>>> {
        return productRepository.getAllCategories()
    }
}
package com.flexath.findit.major.domain.use_cases

import com.flexath.core.utils.Resource
import com.flexath.findit.major.domain.model.HistoryVO
import com.flexath.findit.major.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSearchHistoryUseCase @Inject constructor(
    private val productRepository: ProductRepository
) {
    operator fun invoke(): Flow<Resource<List<HistoryVO>>> {
        return productRepository.getAllSearchHistory()
    }
}
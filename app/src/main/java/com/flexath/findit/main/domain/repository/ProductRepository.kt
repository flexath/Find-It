package com.flexath.findit.main.domain.repository

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.domain.model.ProductVO
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    fun getAllProducts(

    ): Flow<Resource<List<ProductVO>>>


    fun getProduct(
        productId: Int
    ): Flow<Resource<ProductVO>>

    fun getAllCategories(): Flow<Resource<List<String>>>

    fun getProductsOfCategory(
        categoryName: String
    ): Flow<Resource<List<ProductVO>>>

    fun searchProducts(query: String): Flow<Resource<List<ProductVO>>>

    suspend fun insertSearchHistory(query: String)

    suspend fun deleteSearchHistory(id: Int)

    fun getAllSearchHistory(): Flow<Resource<List<HistoryVO>>>
}
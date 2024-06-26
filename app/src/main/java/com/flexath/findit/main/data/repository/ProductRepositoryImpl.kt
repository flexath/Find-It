package com.flexath.findit.main.data.repository

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.data.local.ProductDatabase
import com.flexath.findit.main.data.remote.api.ProductApi
import com.flexath.findit.main.domain.model.HistoryVO
import com.flexath.findit.main.domain.model.ProductVO
import com.flexath.findit.main.domain.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ProductRepositoryImpl @Inject constructor(
    private val productApi: ProductApi,
    private val productDatabase: ProductDatabase
): ProductRepository {

    override fun getAllProducts(): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteProductList = productApi.getAllProducts().toProductListResponse().products
            productDatabase.productDao().deleteProductList()
            productDatabase.productDao().insertProductList(remoteProductList ?: emptyList())
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = listOf()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = listOf()
            ))
        }

        emit(Resource.Success(
            data = productDatabase.productDao().getProductList()
        ))
    }

    override fun getProduct(productId: Int): Flow<Resource<ProductVO>> = flow {
        emit(Resource.Loading())

        try {
            val product = productApi.getProduct(productId = productId).toProductVO()
            emit(Resource.Success(
                data = product
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = null
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = null
            ))
        }
    }

    override fun getAllCategories(): Flow<Resource<List<String>>> = flow {
        emit(Resource.Loading())

        try {
            val productList = productApi.getAllCategories().map {
                it.name ?: ""
            }
            emit(Resource.Success(
                data = productList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = emptyList()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = emptyList()
            ))
        }
    }

    override fun getProductsOfCategory(categoryName: String): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val remoteProductList = productApi.getProductsOfCategory(categoryName = categoryName).toProductListResponse().products
            emit(Resource.Success(
                data = remoteProductList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = listOf()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = listOf()
            ))
        }
    }

    override fun searchProducts(query: String): Flow<Resource<List<ProductVO>>> = flow {
        emit(Resource.Loading())

        try {
            val productList = productApi.searchProducts(query = query).toProductListResponse().products
            emit(Resource.Success(
                data = productList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = emptyList()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't reach server, check your internet connection.",
                data = emptyList()
            ))
        }
    }

    override suspend fun insertSearchHistory(query: String) {
        productDatabase.productDao().insertSearchHistory(
            HistoryVO(
                query = query
            )
        )
    }

    override suspend fun deleteSearchHistory(id: Int) {
        productDatabase.productDao().deleteSearchHistory(id = id)
    }

    override fun getAllSearchHistory(): Flow<Resource<List<HistoryVO>>> = flow {
        emit(Resource.Loading())

        try {
            val searchHistoryList = productDatabase.productDao().getAllSearchHistory()
            emit(Resource.Success(
                data = searchHistoryList
            ))
        } catch(e: HttpException) {
            emit(Resource.Error(
                message = "Oops, something went wrong!",
                data = emptyList()
            ))
        } catch(e: IOException) {
            emit(Resource.Error(
                message = "Couldn't fetch from local database, try again",
                data = emptyList()
            ))
        }
    }
}
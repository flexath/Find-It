package com.flexath.findit.main.data.repository

import com.flexath.findit.core.utils.Resource
import com.flexath.findit.main.data.local.ProductDatabase
import com.flexath.findit.main.data.remote.api.ProductApi
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

        val productList = productDatabase.productDao().getProductList()
        emit(Resource.Loading(data = productList))

        try {
            val remoteProductList = productApi.getAllProducts().toProductListResponse().products
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
            val productList = productApi.getAllCategories().toList()
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

}
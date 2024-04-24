package com.flexath.findit.main.data.remote.api

import com.flexath.findit.core.data.ApiConstants.PATH_CATEGORY_NAME
import com.flexath.findit.core.data.ApiConstants.PATH_PRODUCT_ID
import com.flexath.findit.core.data.ApiConstants.QUERY_LIMIT
import com.flexath.findit.core.data.ApiConstants.QUERY_STRING
import com.flexath.findit.main.data.remote.dto.ProductCategoryListDto
import com.flexath.findit.main.data.remote.dto.ProductDto
import com.flexath.findit.main.data.remote.dto.ProductListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ProductApi {

    @GET("products")
    suspend fun getAllProducts(
        @Query(QUERY_LIMIT) limit:Int = 80
    ): ProductListResponseDto

    @GET("products/{${PATH_PRODUCT_ID}}")
    suspend fun getProduct(
        @Path(PATH_PRODUCT_ID) productId:Int
    ): ProductDto

    @GET("products/categories")
    suspend fun getAllCategories(): ProductCategoryListDto

    @GET("products/category/{${PATH_CATEGORY_NAME}}")
    suspend fun getProductsOfCategory(
        @Path(PATH_CATEGORY_NAME) categoryName:String
    ): ProductListResponseDto

    @GET("products/search")
    suspend fun searchProducts(
        @Query(QUERY_STRING) query: String
    ): ProductListResponseDto
}
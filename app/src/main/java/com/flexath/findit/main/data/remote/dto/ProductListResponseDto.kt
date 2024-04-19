package com.flexath.findit.main.data.remote.dto

import com.flexath.findit.main.domain.model.ProductListResponse

data class ProductListResponseDto(
    val limit: Int?,
    val products: List<ProductDto>?,
    val skip: Int?,
    val total: Int?
) {
    fun toProductListResponse(): ProductListResponse {
        return ProductListResponse(
            products = products?.map {
                it.toProductVO()
            }
        )
    }
}
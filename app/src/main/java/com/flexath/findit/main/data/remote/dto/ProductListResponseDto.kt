package com.flexath.findit.main.data.remote.dto

import com.flexath.findit.main.domain.model.ProductListResponse
import com.google.gson.annotations.SerializedName

data class ProductListResponseDto(
    @SerializedName("limit")
    val limit: Int?,

    @SerializedName("products")
    val products: List<ProductDto>?,

    @SerializedName("skip")
    val skip: Int?,

    @SerializedName("total")
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
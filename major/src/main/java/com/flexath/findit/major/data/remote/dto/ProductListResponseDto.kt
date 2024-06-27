package com.flexath.findit.major.data.remote.dto

import com.flexath.findit.major.domain.model.ProductListResponse
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
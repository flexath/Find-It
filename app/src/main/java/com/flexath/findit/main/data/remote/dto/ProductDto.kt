package com.flexath.findit.main.data.remote.dto

import com.flexath.findit.main.domain.model.ProductVO

data class ProductDto(
    val brand: String?,
    val category: String?,
    val description: String?,
    val discountPercentage: Double?,
    val id: Int?,
    val images: List<String>?,
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val thumbnail: String?,
    val title: String?
) {
    fun toProductVO(): ProductVO {
        return ProductVO(
            id = id,
            title = title,
            description = description,
            price = price,
            rating = rating,
            stock = stock,
            brand = brand,
            thumbnail = thumbnail,
            images = images
        )
    }
}
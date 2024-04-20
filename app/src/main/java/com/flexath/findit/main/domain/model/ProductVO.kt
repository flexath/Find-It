package com.flexath.findit.main.domain.model

data class ProductVO(
    val title: String?,
    val description: String? = "",
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,
    val thumbnail: String?,
    val images: List<String>?
)

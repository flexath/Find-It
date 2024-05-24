package com.flexath.findit.main.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ProductCategoryItemDto(
    @SerializedName("name")
    val name: String?,

    @SerializedName("slug")
    val slug: String?,

    @SerializedName("url")
    val url: String?
)
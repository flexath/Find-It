package com.flexath.findit.main.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MetaDto(
    @SerializedName("barcode")
    val barcode: String?,

    @SerializedName("createdAt")
    val createdAt: String?,

    @SerializedName("qrCode")
    val qrCode: String?,

    @SerializedName("updatedAt")
    val updatedAt: String?
)
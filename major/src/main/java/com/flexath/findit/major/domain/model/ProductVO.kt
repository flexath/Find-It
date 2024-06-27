package com.flexath.findit.major.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.findit.major.data.local.type_converters.ListStringTypeConverter

@Entity(tableName = "product_table")
@TypeConverters(
    com.flexath.findit.major.data.local.type_converters.ListStringTypeConverter::class
)
data class ProductVO(
    @PrimaryKey val id: Int = 0,
    val title: String?,
    val description: String? = "",
    val price: Int?,
    val rating: Double?,
    val stock: Int?,
    val brand: String?,
    val thumbnail: String?,
    val images: List<String>?
)

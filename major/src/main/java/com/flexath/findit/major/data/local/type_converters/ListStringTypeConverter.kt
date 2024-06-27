package com.flexath.findit.major.data.local.type_converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ListStringTypeConverter {
    @TypeConverter
    fun toString(genreIds: List<String>?) :String {
        return Gson().toJson(genreIds)
    }

    @TypeConverter
    fun toCategory(jsonString:String) : List<String>? {
        val categoryType = object : TypeToken<List<String>?>(){}.type
        return Gson().fromJson(jsonString,categoryType)
    }
}
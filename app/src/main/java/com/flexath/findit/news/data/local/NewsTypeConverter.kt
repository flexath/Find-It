package com.flexath.findit.news.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.flexath.findit.news.domain.model.SourceVO

@ProvidedTypeConverter
class NewsTypeConverter {

    @TypeConverter
    fun sourceToString(source: SourceVO): String {
        return "${source.id},${source.name}"
    }

    @TypeConverter
    fun stringToSource(source: String): SourceVO {
        return source.split(",").let { sourceArray ->
            SourceVO(
                id = sourceArray[0],
                name = sourceArray[1]
            )
        }
    }
}
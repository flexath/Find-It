package com.flexath.news.domain.model

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.news.data.local.NewsTypeConverter
import kotlinx.parcelize.Parcelize
import javax.annotation.Nonnull

@Parcelize
@Entity
@TypeConverters(com.flexath.news.data.local.NewsTypeConverter::class)
data class ArticleVO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceVO?,
    val title: String?,
    @PrimaryKey val url: String = "",
    val urlToImage: String?
): Parcelable {
    fun formatPublishedAtTime(): String {
        return publishedAt?.indexOfFirst {
            it == 'T'
        }?.let { publishedAt.removeRange(it,publishedAt.lastIndex+1) } ?: ""
    }
}
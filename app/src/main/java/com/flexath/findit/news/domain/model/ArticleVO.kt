package com.flexath.findit.news.domain.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class ArticleVO(
    val author: String?,
    val content: String?,
    val description: String?,
    val publishedAt: String?,
    val source: SourceVO?,
    val title: String?,
    @PrimaryKey val url: String,
    val urlToImage: String?
): Parcelable
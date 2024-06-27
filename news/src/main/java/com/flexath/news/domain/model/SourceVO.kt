package com.flexath.news.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceVO(
    val id: String?,
    val name: String?
): Parcelable
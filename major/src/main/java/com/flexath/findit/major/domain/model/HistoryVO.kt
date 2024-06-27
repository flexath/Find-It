package com.flexath.findit.major.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("history_table")
data class HistoryVO(
    @ColumnInfo("query")
    val query: String?
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}

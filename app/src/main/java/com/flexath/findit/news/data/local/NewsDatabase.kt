package com.flexath.findit.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.flexath.findit.news.domain.model.ArticleVO

@Database(entities = [ArticleVO::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: NewsDao
}
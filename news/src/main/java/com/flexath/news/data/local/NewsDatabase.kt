package com.flexath.news.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.flexath.news.domain.model.ArticleVO

@Database(entities = [ArticleVO::class], version = 1, exportSchema = false)
abstract class NewsDatabase : RoomDatabase() {
    abstract val newsDao: com.flexath.news.data.local.NewsDao
}
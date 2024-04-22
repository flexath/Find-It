package com.flexath.findit.news.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.flexath.findit.news.domain.model.ArticleVO

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertArticles(articleList: List<ArticleVO>)

    @Query("SELECT * FROM ArticleVO")
    suspend fun getArticles() : List<ArticleVO>
}
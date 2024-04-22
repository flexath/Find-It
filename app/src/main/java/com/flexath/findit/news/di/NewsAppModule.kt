package com.flexath.findit.news.di

import android.content.Context
import androidx.room.Room
import com.flexath.findit.core.data.ApiConstants
import com.flexath.findit.news.data.local.NewsDao
import com.flexath.findit.news.data.local.NewsDatabase
import com.flexath.findit.news.data.local.NewsTypeConverter
import com.flexath.findit.news.data.remote.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsAppModule {

    @Provides
    @Singleton
    fun provideNewsApi(): NewsApi = Retrofit.Builder()
        .baseUrl(ApiConstants.NEWS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(NewsApi::class.java)

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = NewsDatabase::class.java,
            name = "NewsDataBase"
        ).addTypeConverter(NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: NewsDatabase): NewsDao = database.newsDao
}
package com.flexath.news.di

import android.content.Context
import androidx.room.Room
import com.flexath.news.data.remote.api.NewsApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NewsAppModule {

    @Provides
    @Singleton
    fun provideNewsRetrofitApi(@Named("newsRetrofit") retrofit: Retrofit) : NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    @Singleton
    @Named("newsRetrofit")
    fun provideNewsApi(): Retrofit = Retrofit.Builder()
        .baseUrl(com.flexath.core.data.ApiConstants.NEWS_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideNewsDatabase(@ApplicationContext context: Context): com.flexath.news.data.local.NewsDatabase =
        Room.databaseBuilder(
            context = context,
            klass = com.flexath.news.data.local.NewsDatabase::class.java,
            name = "NewsDataBase"
        ).addTypeConverter(com.flexath.news.data.local.NewsTypeConverter())
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    @Singleton
    fun provideNewsDao(database: com.flexath.news.data.local.NewsDatabase): com.flexath.news.data.local.NewsDao = database.newsDao
}
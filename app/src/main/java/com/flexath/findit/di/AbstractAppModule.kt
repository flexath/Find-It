package com.flexath.findit.di

import com.flexath.findit.major.data.repository.ProductRepositoryImpl
import com.flexath.findit.major.domain.repository.ProductRepository
import com.flexath.news.data.repository.NewsRepositoryImpl
import com.flexath.news.domain.repository.NewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AbstractAppModule {
    @Binds
    abstract fun provideProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideNewsRepository(repositoryImpl: NewsRepositoryImpl): NewsRepository
}
package com.flexath.findit.core.di

import com.flexath.findit.main.data.repository.ProductRepositoryImpl
import com.flexath.findit.main.domain.repository.ProductRepository
import com.flexath.findit.news.domain.repository.NewsRepository
import com.flexath.findit.news.data.repository.NewsRepositoryImpl
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
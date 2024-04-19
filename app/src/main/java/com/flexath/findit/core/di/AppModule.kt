package com.flexath.findit.core.di

import com.flexath.findit.main.data.repository.ProductRepositoryImpl
import com.flexath.findit.main.domain.repository.ProductRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideJobRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository
}
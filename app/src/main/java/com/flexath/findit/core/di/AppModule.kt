package com.flexath.findit.core.di

import android.content.Context
import com.flexath.findit.main.data.repository.ProductRepositoryImpl
import com.flexath.findit.main.domain.repository.ProductRepository
import com.flexath.findit.news.data.manager.LocalUserManagerImpl
import com.flexath.findit.news.domain.manager.LocalUserManager
import com.flexath.findit.news.domain.repository.NewsRepository
import com.flexath.findit.news.domain.usecases.app_entry.AppEntryUseCases
import com.flexath.findit.news.domain.usecases.app_entry.ReadAppEntryUseCase
import com.flexath.findit.news.domain.usecases.app_entry.SaveAppEntryUseCase
import com.flexath.findit.news.data.repository.NewsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    abstract fun provideProductRepository(repositoryImpl: ProductRepositoryImpl): ProductRepository

    @Binds
    abstract fun provideNewsRepository(repositoryImpl: NewsRepositoryImpl): NewsRepository

    @Provides
    @Singleton
    fun provideLocalUserManager(@ApplicationContext context: Context): LocalUserManager =
        LocalUserManagerImpl(context)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCases =
        AppEntryUseCases(
            readAppEntry = ReadAppEntryUseCase(localUserManager),
            saveAppEntry = SaveAppEntryUseCase(localUserManager)
        )
}
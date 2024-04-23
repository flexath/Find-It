package com.flexath.findit.core.di

import android.content.Context
import com.flexath.findit.news.data.manager.LocalUserManagerImpl
import com.flexath.findit.news.domain.manager.LocalUserManager
import com.flexath.findit.news.domain.usecases.app_entry.AppEntryUseCases
import com.flexath.findit.news.domain.usecases.app_entry.ReadAppEntryUseCase
import com.flexath.findit.news.domain.usecases.app_entry.SaveAppEntryUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

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
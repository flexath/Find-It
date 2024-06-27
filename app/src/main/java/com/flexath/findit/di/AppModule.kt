package com.flexath.findit.di

import android.content.Context
import com.flexath.core.data.manager.LocalUserManagerImpl
import com.flexath.core.domain.manager.LocalUserManager
import com.flexath.core.domain.use_cases.AppEntryUseCase
import com.flexath.core.domain.use_cases.ReadAppEntryUseCase
import com.flexath.core.domain.use_cases.SaveAppEntryCase
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
    fun provideAppEntryUseCases(localUserManager: LocalUserManager): AppEntryUseCase =
        AppEntryUseCase(
            readAppEntry = ReadAppEntryUseCase(localUserManager),
            saveAppEntry = SaveAppEntryCase(localUserManager)
        )
}
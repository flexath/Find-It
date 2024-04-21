package com.flexath.findit.main.di

import android.content.Context
import androidx.room.Room
import com.flexath.findit.core.data.ApiConstants.DUMMY_JSON_BASE_URL
import com.flexath.findit.main.data.local.ProductDatabase
import com.flexath.findit.main.data.remote.api.ProductApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductAppModule {

    @Provides
    fun provideOkHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(DUMMY_JSON_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit) : ProductApi {
        return retrofit.create(ProductApi::class.java)
    }

    @Volatile
    private var INSTANCE: ProductDatabase? = null

    @Provides
    @Singleton
    fun provideJobDatabaseInstance(@ApplicationContext context: Context) = INSTANCE ?: synchronized(this) {
        INSTANCE ?: Room.databaseBuilder(
            context,
            ProductDatabase::class.java,
            "job_database"
        ).fallbackToDestructiveMigration()
            .build().also {
                INSTANCE = it
            }
    }

    @Provides
    @Singleton
    fun provideJobDao(database: ProductDatabase) = database.productDao()

    // For LiveNetworkChecker
//    @Provides
//    fun provideLiveNetworkChecker(@ApplicationContext context: Context) : ConnectivityManager {
//        return context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
//    }
}
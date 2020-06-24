package com.easymanga.di.module

import android.content.Context
import com.easymanga.data.network.AppNetworkDataManager
import com.easymanga.data.network.DownloadManager
import com.easymanga.data.network.NetworkDataManager
import com.easymanga.di.ApplicationContext
import com.easymanga.util.rx.SchedulerProvider
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetModule(val baseUrl: String) {

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl(baseUrl)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitApi(retrofit: Retrofit): NetworkDataManager.RetrofitApi {
        return retrofit.create(NetworkDataManager.RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideNetworkDataManager(
        retrofitNetworkData: NetworkDataManager.RetrofitApi
    ): NetworkDataManager {
        return AppNetworkDataManager(retrofitNetworkData)
    }

    @Provides
    @Singleton
    fun provideDownloadManager(
        networkDataManager: NetworkDataManager,
        schedulerProvider: SchedulerProvider,
        @ApplicationContext context: Context
    ): DownloadManager {
        return DownloadManager(networkDataManager, schedulerProvider, context)
    }
}
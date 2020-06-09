package com.easymanga.di.module

import com.easymanga.data.network.ApiManager
import com.easymanga.data.network.AppApiManager
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
    fun provideRetrofitApi(retrofit: Retrofit): ApiManager.RetrofitApi {
        return retrofit.create(ApiManager.RetrofitApi::class.java)
    }

    @Provides
    @Singleton
    fun provideApiManager(retrofitApi: ApiManager.RetrofitApi): ApiManager {
        return AppApiManager(retrofitApi)
    }
}
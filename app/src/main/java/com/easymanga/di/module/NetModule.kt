package com.easymanga.di.module

import com.easymanga.data.network.AppNetworkDataManager
import com.easymanga.data.network.NetworkDataManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [NetModule.BindsModule::class])
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

    @Module
    interface BindsModule {

        @Binds
        fun provideNetworkDataManager(networkDataManager: AppNetworkDataManager): NetworkDataManager
    }
}
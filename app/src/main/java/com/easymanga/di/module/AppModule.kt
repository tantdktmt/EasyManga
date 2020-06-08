package com.easymanga.di.module

import android.content.Context
import com.easymanga.EasyMangaApplication
import com.easymanga.debug.Person
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var context: EasyMangaApplication) {

    @Singleton
    @Provides
    fun provideApplicationContext(): Context {
        return context
    }
}
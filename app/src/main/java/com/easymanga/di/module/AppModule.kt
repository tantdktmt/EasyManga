package com.easymanga.di.module

import android.content.Context
import com.easymanga.EasyMangaApplication
import com.easymanga.debug.Person
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private var context: EasyMangaApplication) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context
    }

    @Provides
    @Singleton
    fun providePerson(): Person {
        return Person("Tran Duc Tan", 30)
    }
}
package com.easymanga.di.module

import android.content.Context
import com.easymanga.EasyMangaApplication
import com.easymanga.debug.Person
import com.easymanga.util.rx.AppSchedulerProvider
import com.easymanga.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
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

    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider {
        return AppSchedulerProvider()
    }

    @Provides
    fun provideCompositeDisposable(): CompositeDisposable {
        return CompositeDisposable()
    }
}
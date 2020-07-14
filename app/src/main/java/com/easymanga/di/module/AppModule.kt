package com.easymanga.di.module

import android.content.Context
import com.easymanga.EasyMangaApplication
import com.easymanga.di.ApplicationContext
import com.easymanga.util.rx.AppSchedulerProvider
import com.easymanga.util.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Singleton

@Module
class AppModule(private var context: EasyMangaApplication) {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return context
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
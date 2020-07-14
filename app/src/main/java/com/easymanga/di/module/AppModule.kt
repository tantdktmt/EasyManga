package com.easymanga.di.module

import android.app.Application
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
class AppModule(private var application: EasyMangaApplication) {

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return application
    }

    @Provides
    @Singleton
    fun provideApplication(): Application {
        return application
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
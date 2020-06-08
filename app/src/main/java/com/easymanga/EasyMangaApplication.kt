package com.easymanga

import android.app.Application
import com.easymanga.di.component.AppComponent
import com.easymanga.di.component.DaggerAppComponent
import com.easymanga.di.module.AppModule

class EasyMangaApplication : Application() {

    companion object {

        private val DEBUG_SUB_TAG = "[" + EasyMangaApplication::class.java.simpleName + "] "

        private lateinit var instance: EasyMangaApplication

        fun getInstance(): EasyMangaApplication {
            return instance
        }
    }

    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        appComponent = DaggerAppComponent.builder().appModule(AppModule(this)).build()
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}
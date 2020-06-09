package com.easymanga

import android.app.Application
import com.easymanga.data.network.ApiEndPoint
import com.easymanga.di.component.AppComponent

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
        appComponent = AppComponent.create(this, ApiEndPoint.BASE_URL)
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }
}
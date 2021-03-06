package com.easymanga

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.downloader.PRDownloader
import com.downloader.PRDownloaderConfig
import com.easymanga.data.network.EndPoint
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
        appComponent = AppComponent.create(this, EndPoint.BASE_URL)
        PRDownloader.initialize(
            this,
            PRDownloaderConfig.newBuilder().setDatabaseEnabled(true).build()
        )
    }

    fun getAppComponent(): AppComponent {
        return appComponent
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }
}
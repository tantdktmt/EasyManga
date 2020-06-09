package com.easymanga.di.component

import com.easymanga.EasyMangaApplication
import com.easymanga.HomeFragment
import com.easymanga.di.module.AppModule
import com.easymanga.di.module.NetModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class])
interface AppComponent {

    companion object Factory {

        fun create(application: EasyMangaApplication, baseUrl: String): AppComponent {
            return DaggerAppComponent.builder().appModule(AppModule(application))
                .netModule(NetModule(baseUrl))
                .build()
        }
    }

    fun inject(fragment: HomeFragment)
}
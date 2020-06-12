package com.easymanga.di.component

import com.easymanga.EasyMangaApplication
import com.easymanga.di.module.AppModule
import com.easymanga.di.module.NetModule
import com.easymanga.di.module.ViewModelModule
import com.easymanga.ui.episodelist.EpisodeListFragment
import com.easymanga.ui.episodelist.EpisodeListViewModel
import com.easymanga.ui.mangalist.MangaListFragment
import com.easymanga.ui.mangalist.MangaListViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetModule::class, ViewModelModule::class])
interface AppComponent {

    companion object Factory {

        fun create(application: EasyMangaApplication, baseUrl: String): AppComponent {
            return DaggerAppComponent.builder().appModule(AppModule(application))
                .netModule(NetModule(baseUrl))
                .build()
        }
    }

    fun inject(fragment: MangaListFragment)

    fun inject(fragment: EpisodeListFragment)

    fun inject(viewModel: MangaListViewModel)

    fun inject(viewModel: EpisodeListViewModel)
}
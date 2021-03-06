package com.easymanga.di.component

import com.easymanga.EasyMangaApplication
import com.easymanga.di.module.AppModule
import com.easymanga.di.module.NetModule
import com.easymanga.di.module.ViewModelModule
import com.easymanga.ui.MainActivity
import com.easymanga.ui.SharedViewModel
import com.easymanga.ui.base.BaseActivity
import com.easymanga.ui.base.BaseFragment
import com.easymanga.ui.episodedetail.EpisodeDetailFragment
import com.easymanga.ui.mangadetail.page.EpisodeListFragment
import com.easymanga.ui.mangalist.MangaListFragment
import com.easymanga.ui.storage.StorageFragment
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

    fun inject(activity: BaseActivity)

    fun inject(activity: MainActivity)

    fun inject(fragment: BaseFragment)

    fun inject(fragment: MangaListFragment)

    fun inject(fragment: EpisodeListFragment)

    fun inject(fragment: EpisodeDetailFragment)

    fun inject(fragment: StorageFragment)

    fun inject(viewModel: SharedViewModel)
}
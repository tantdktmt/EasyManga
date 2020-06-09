package com.easymanga.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easymanga.ui.base.ViewModelFactory
import com.easymanga.di.ViewModelKey
import com.easymanga.ui.episodelist.EpisodeListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(EpisodeListViewModel::class)
    internal abstract fun episodeListViewModel(viewModel: EpisodeListViewModel): ViewModel
}
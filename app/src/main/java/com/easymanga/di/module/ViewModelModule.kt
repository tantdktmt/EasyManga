package com.easymanga.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.easymanga.di.ViewModelKey
import com.easymanga.ui.SharedViewModel
import com.easymanga.ui.base.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(SharedViewModel::class)
    internal abstract fun sharedViewModel(viewModel: SharedViewModel): ViewModel
}
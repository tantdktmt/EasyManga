package com.easymanga.di.component

import com.easymanga.HomeFragment
import com.easymanga.di.module.AppModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {

    fun inject(fragment: HomeFragment)
}
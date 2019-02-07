package com.vkompaniets.githubreader.di

import com.vkompaniets.githubreader.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
}
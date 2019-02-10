package com.vkompaniets.githubreader.di

import com.vkompaniets.githubreader.ui.MainActivity
import com.vkompaniets.githubreader.ui.search.SearchFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(mainActivity: MainActivity)
    fun inject(searchFragment: SearchFragment)
}
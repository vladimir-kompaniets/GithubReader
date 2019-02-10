package com.vkompaniets.githubreader.di

import com.vkompaniets.githubreader.ui.search.SearchFragment
import com.vkompaniets.githubreader.ui.user.UserDetailsFragment
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class, ViewModelModule::class])
@Singleton
interface AppComponent {

    fun inject(searchFragment: SearchFragment)
    fun inject(userDetailsFragment: UserDetailsFragment)
}
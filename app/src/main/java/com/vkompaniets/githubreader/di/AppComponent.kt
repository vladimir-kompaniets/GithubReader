package com.vkompaniets.githubreader.di

import dagger.Component
import javax.inject.Singleton

@Component(modules = [ServiceModule::class])
@Singleton
interface AppComponent {
}
package com.vkompaniets.githubreader

import android.app.Application
import com.vkompaniets.githubreader.di.AppComponent
import com.vkompaniets.githubreader.di.DaggerAppComponent

class GithubReaderApp: Application() {

    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: GithubReaderApp
            private set
    }
}
package com.example.heretochess

import android.app.Application
import com.example.heretochess.dagger.AppComponent
import com.example.heretochess.dagger.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.create()
    }
}
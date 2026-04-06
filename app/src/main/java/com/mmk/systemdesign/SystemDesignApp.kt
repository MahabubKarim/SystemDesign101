package com.mmk.systemdesign

import android.app.Application
import com.mmk.systemdesign.di.AppContainer

class SystemDesignApp : Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = AppContainer()
    }
}

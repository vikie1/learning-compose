package com.github.vikie1.amphibians

import android.app.Application
import com.github.vikie1.amphibians.data.AppContainer
import com.github.vikie1.amphibians.data.DefaultAppContainer

class AmphibiansApp: Application() {
    lateinit var appContainer: AppContainer

    override fun onCreate() {
        super.onCreate()
        appContainer = DefaultAppContainer()
    }
}
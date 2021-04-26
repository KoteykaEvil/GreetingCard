package com.example.greetingcard

import android.app.Application
import com.example.greetingcard.di.app_modules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(app_modules)
        }
    }
}
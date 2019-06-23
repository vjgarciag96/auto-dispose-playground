package com.vjgarcia.autodisposableplayground

import android.app.Application
import com.vjgarcia.autodisposableplayground.presentation.secondsPresentationModulew
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AndroidApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApplication)
            modules(secondsPresentationModulew)
        }
    }
}
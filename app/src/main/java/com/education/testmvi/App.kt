package com.education.testmvi

import android.app.Application
import com.education.testmvi.injector.Injector
import com.education.testmvi.injector.MainActivityInjector
import com.education.testmvi.presentation.MainActivity
import timber.log.Timber

class App : Application() {

    companion object {
        lateinit var injector: Injector<MainActivity>
        fun injectMainActivity(context: MainActivity) {
            injector.inject(context)
        }
    }

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
        injector = MainActivityInjector()
    }
}
package com.example.tribecrypto

import android.app.Application
import com.example.tribecrypto.di.component.ApplicationComponent
import com.example.tribecrypto.di.component.DaggerApplicationComponent
import timber.log.Timber

class ApplicationClass : Application() {

    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.asTree())
        applicationComponent = DaggerApplicationComponent.builder()
            .build()

        applicationComponent.inject(this)
    }
}
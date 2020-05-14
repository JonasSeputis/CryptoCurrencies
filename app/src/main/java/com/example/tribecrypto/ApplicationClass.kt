package com.example.tribecrypto

import android.app.Application
import android.content.Context
import com.example.tribecrypto.di.component.ApplicationComponent
import com.example.tribecrypto.di.component.DaggerApplicationComponent
import com.example.tribecrypto.di.module.AppModule
import com.example.tribecrypto.di.module.DatabaseModule
import timber.log.Timber

class ApplicationClass : Application() {

    lateinit var applicationComponent: ApplicationComponent

    companion object {
        fun getApplicationComponent(context: Context): ApplicationComponent {
            val app = context.applicationContext as ApplicationClass
            app.applicationComponent = DaggerApplicationComponent
                .builder()
                .appModule(AppModule(app))
                .databaseModule(DatabaseModule(app))
                .build()
            return app.applicationComponent
        }
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        getApplicationComponent(this).inject(this)
    }
}
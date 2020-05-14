package com.example.tribecrypto.di.module

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class AppModule(val application: Application) {

    @Provides
    fun providesApplication(): Application {
        return application
    }
}
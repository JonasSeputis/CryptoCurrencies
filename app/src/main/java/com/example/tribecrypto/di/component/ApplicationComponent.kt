package com.example.tribecrypto.di.component

import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.di.module.NetModule
import dagger.Component

@Component(modules = [NetModule::class])
interface ApplicationComponent {
    //Add implementations
    fun inject(application: ApplicationClass)
}
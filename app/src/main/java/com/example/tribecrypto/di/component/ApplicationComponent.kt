package com.example.tribecrypto.di.component

import android.app.Application
import com.example.tribecrypto.Api
import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.database.CurrencyDao
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.database.Database
import com.example.tribecrypto.di.module.DatabaseModule
import com.example.tribecrypto.di.module.NetModule
import dagger.Component

@Component(modules = [Application::class, NetModule::class, DatabaseModule::class])
interface ApplicationComponent {
    //Add implementations
    fun inject(application: ApplicationClass)

    val currencyDao: CurrencyDao
    val currencyRepository: CurrencyRepository
    val database: Database
    val api: Api
    val application: Application
}
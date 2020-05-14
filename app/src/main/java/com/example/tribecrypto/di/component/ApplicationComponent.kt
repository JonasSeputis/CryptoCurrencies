package com.example.tribecrypto.di.component

import android.app.Application
import com.example.tribecrypto.Api
import com.example.tribecrypto.ApplicationClass
import com.example.tribecrypto.database.CurrencyDao
import com.example.tribecrypto.database.Database
import com.example.tribecrypto.database.currency_details.DetailsDao
import com.example.tribecrypto.database.watchlist.WatchListDao
import com.example.tribecrypto.di.module.AppModule
import com.example.tribecrypto.di.module.DatabaseModule
import com.example.tribecrypto.di.module.NetModule
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.repository.WatchListRepository
import dagger.Component

@Component(modules = [AppModule::class, NetModule::class, DatabaseModule::class])
interface ApplicationComponent {
    fun inject(application: ApplicationClass)

    val currencyDao: CurrencyDao
    val detailsDao: DetailsDao
    val watchListDao: WatchListDao
    val currencyRepository: CurrencyRepository
    val currencyDetailsRepository: CurrencyDetailsRepository
    val watchListRepository: WatchListRepository
    val database: Database
    val api: Api
    val application: Application
}
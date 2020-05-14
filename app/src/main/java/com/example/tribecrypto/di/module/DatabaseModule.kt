package com.example.tribecrypto.di.module

import android.app.Application
import androidx.room.Room
import com.example.tribecrypto.Api
import com.example.tribecrypto.database.CurrencyDao
import com.example.tribecrypto.database.CurrencyDataSource
import com.example.tribecrypto.database.Database
import com.example.tribecrypto.database.currency_details.DetailsDao
import com.example.tribecrypto.database.currency_details.DetailsDataSource
import com.example.tribecrypto.database.watchlist.WatchListDao
import com.example.tribecrypto.database.watchlist.WatchListDataSource
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.repository.WatchListRepository
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

    private var database: Database

    constructor(application: Application) {
        database = Room.databaseBuilder(application, Database::class.java, "database").build()
    }

    @Provides
    fun providesDatabase() : Database {
        return database
    }

    @Provides
    fun providesCurrencyDao(database: Database) : CurrencyDao {
        return database.getCurrencyDao()
    }

    @Provides
    fun providesCurrencyDetailsDao(database: Database): DetailsDao {
        return database.getCurrencyDetailsDao()
    }

    @Provides
    fun providesWatchListDao(database: Database): WatchListDao {
        return database.getWatchListDao()
    }

    @Provides
    fun currencyRepository(currencyDao: CurrencyDao, api: Api) : CurrencyRepository {
        return CurrencyDataSource(currencyDao, api)
    }

    @Provides
    fun currencyDetailsRepository(currencyDetailsDao: DetailsDao) : CurrencyDetailsRepository {
        return DetailsDataSource(currencyDetailsDao)
    }

    @Provides
    fun watchListRepository(watchListDao: WatchListDao): WatchListRepository {
        return WatchListDataSource(watchListDao)
    }
}
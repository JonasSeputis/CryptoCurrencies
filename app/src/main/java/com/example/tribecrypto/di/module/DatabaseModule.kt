package com.example.tribecrypto.di.module

import android.app.Application
import androidx.room.Room
import com.example.tribecrypto.Api
import com.example.tribecrypto.database.CurrencyDao
import com.example.tribecrypto.database.CurrencyDataSource
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.database.Database
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    private lateinit var database: Database

    constructor(application: Application) {
        database = Room.databaseBuilder(application, Database::class.java, "database").build()
    }

    @Singleton
    @Provides
    fun providesDatabase() : Database {
        return database
    }

    @Singleton
    @Provides
    fun providesCurrencyDao(database: Database) : CurrencyDao {
        return database.getCurrencyDao()
    }

    @Singleton
    @Provides
    fun currencyRepository(productDao: CurrencyDao, api: Api) : CurrencyRepository {
        return CurrencyDataSource(productDao, api)
    }
}
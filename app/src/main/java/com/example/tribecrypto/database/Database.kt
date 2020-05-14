package com.example.tribecrypto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.database.currency_details.DetailsDao
import com.example.tribecrypto.database.watchlist.WatchListDao

@Database(
    entities = [CryptoCurrencyEntity::class, CryptoCurrencyDetailsEntity::class, WatchListEntity::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    val version: Int = 1

    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getCurrencyDetailsDao(): DetailsDao
    abstract fun getWatchListDao(): WatchListDao
}
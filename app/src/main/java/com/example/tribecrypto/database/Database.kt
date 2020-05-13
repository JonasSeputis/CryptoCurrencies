package com.example.tribecrypto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tribecrypto.data.CryptoCurrencyEntity
import com.example.tribecrypto.data.CryptoCurrencyDetailsEntity
import com.example.tribecrypto.database.currencyDetails.DetailsDao

@Database(
    entities = [CryptoCurrencyEntity::class, CryptoCurrencyDetailsEntity::class],
    version = 1,
    exportSchema = true
)
abstract class Database : RoomDatabase() {
    val version: Int = 1

    abstract fun getCurrencyDao(): CurrencyDao
    abstract fun getCurrencyDetailsDao(): DetailsDao
}
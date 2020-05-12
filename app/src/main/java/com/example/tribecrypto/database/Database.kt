package com.example.tribecrypto.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tribecrypto.data.CryptoCurrency
import com.example.tribecrypto.data.CryptoCurrencyQuoteObject

@Database(entities = [CryptoCurrency::class, CryptoCurrencyQuoteObject::class], version = 1)
abstract class Database : RoomDatabase() {
    val version: Int = 1

    abstract fun getCurrencyDao() : CurrencyDao
}
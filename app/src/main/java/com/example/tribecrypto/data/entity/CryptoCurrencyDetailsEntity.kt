package com.example.tribecrypto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_details")
data class CryptoCurrencyDetailsEntity(
    @PrimaryKey @ColumnInfo(name = "currency_name") val currencyName: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "percent_change_1h") val percentChange1h: String,
    @ColumnInfo(name = "percent_change_7d") val percentChange7d: String,
    @ColumnInfo(name = "last_updated") val lastUpdated: String
)
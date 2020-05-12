package com.example.tribecrypto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.math.BigDecimal

@Entity
class CryptoCurrencyQuoteObject(
    @PrimaryKey(autoGenerate = true) val currencyQuoteId: Long = 0,
    @ColumnInfo(name = "price") val price: BigDecimal,
    @ColumnInfo(name = "percent_change_1h") val percentChange1h: BigDecimal,
    @ColumnInfo(name = "percent_change_24h") val percentChange24h: BigDecimal,
    @ColumnInfo(name = "percent_change_7d") val percentChange7d: BigDecimal,
    @ColumnInfo(name = "last_updated") val lastUpdated: String
)
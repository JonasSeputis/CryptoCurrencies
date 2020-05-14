package com.example.tribecrypto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_currency")
data class CryptoCurrencyEntity(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "circulating_supply") val circulatingSupply: String,
    @ColumnInfo(name = "cmc_rank") val cmcRank: Long,
    @ColumnInfo(name = "date_added") val dateAdded: String,
    @ColumnInfo(name = "last_updated") val lastUpdated: String,
    @ColumnInfo(name = "percent_change_24h") val percentChange24h: String
)
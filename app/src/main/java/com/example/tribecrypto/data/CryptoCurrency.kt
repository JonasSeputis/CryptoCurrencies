package com.example.tribecrypto.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "crypto_currency")
class CryptoCurrency(
    @PrimaryKey(autoGenerate = true) val currencyId: Long = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "symbol") val symbol: String,
    @ColumnInfo(name = "slug") val slug: String,
    @ColumnInfo(name = "circulating_supply") val circulatingSupply: Long,
    @ColumnInfo(name = "cmc_rank") val cmcRank: Long,
    @ColumnInfo(name = "date_added") val dateAdded: String,
    @ColumnInfo(name = "last_updated") val lastUpdated: String,
    /**
     *  @watchingCurrency value inside database is used as Boolean regards if it is included to watch list
     *  0 - Currency is not added to watch list
     *  1 - Currency is added to watch list
     */
    @ColumnInfo(name = "watch_listed") val watchingCurrency: Int = 0,
    @SerializedName("quote")
    val cryptoQuote : JsonObject
) {

    val currentCurrencyId = this.currencyId
    val cryptoQuoteObject: JsonObject = this.cryptoQuote
}
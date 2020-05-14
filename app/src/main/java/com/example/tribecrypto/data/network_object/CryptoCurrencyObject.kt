package com.example.tribecrypto.data.network_object

import com.google.gson.annotations.SerializedName

class CryptoCurrencyObject(
@SerializedName("name") val name: String,
@SerializedName("symbol") val symbol: String,
@SerializedName("slug") val slug: String,
@SerializedName("circulating_supply") val circulatingSupply: String,
@SerializedName("cmc_rank") val cmcRank: Long,
@SerializedName("date_added") val dateAdded: String,
@SerializedName("last_updated") val lastUpdated: String,
@SerializedName("quote") val cryptoQuote : CryptoCurrencyQuote
)
package com.example.tribecrypto.data.network_object

import com.google.gson.annotations.SerializedName

class CryptoCurrencyQuoteObject(
    @SerializedName("price") val price: String,
    @SerializedName("percent_change_1h") val percentChange1h: String,
    @SerializedName("percent_change_24h") val percentChange24h: String,
    @SerializedName("percent_change_7d") val percentChange7d: String,
    @SerializedName("last_updated") val lastUpdated: String
)
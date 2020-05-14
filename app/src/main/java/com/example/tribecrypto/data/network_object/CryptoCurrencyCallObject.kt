package com.example.tribecrypto.data.network_object

import com.google.gson.annotations.SerializedName

class CryptoCurrencyCallObject(
    @SerializedName("data")
    val cryptoCurrency: List<CryptoCurrencyObject>
)

package com.example.tribecrypto.data.networkObject

import com.google.gson.annotations.SerializedName

class CryptoCurrencyCallObject(
    @SerializedName("data")
    val cryptoCurrency: List<CryptoCurrencyObject>
)

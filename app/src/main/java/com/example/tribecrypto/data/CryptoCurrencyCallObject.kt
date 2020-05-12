package com.example.tribecrypto.data

import com.google.gson.annotations.SerializedName

class CryptoCurrencyCallObject(
    @SerializedName("data")
    val cryptoCurrency: List<CryptoCurrency>
)

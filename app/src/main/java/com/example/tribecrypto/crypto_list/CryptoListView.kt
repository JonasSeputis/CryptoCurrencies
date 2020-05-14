package com.example.tribecrypto.crypto_list

import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject

interface CryptoListView {

    fun setEntityList(data: CryptoCurrencyAndWatchlistObject)
    fun noInternetConnectionInformation()
}
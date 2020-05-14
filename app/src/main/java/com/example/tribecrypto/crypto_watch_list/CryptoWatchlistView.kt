package com.example.tribecrypto.crypto_watch_list

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity

interface CryptoWatchlistView {

    fun provideCurrencyList(data: List<CryptoCurrencyEntity>)
}
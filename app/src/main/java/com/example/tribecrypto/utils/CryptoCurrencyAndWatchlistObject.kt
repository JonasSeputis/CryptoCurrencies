package com.example.tribecrypto.utils

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity

data class CryptoCurrencyAndWatchlistObject(var currency: List<CryptoCurrencyEntity>, var watchlist: List<WatchListEntity>)
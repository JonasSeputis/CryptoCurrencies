package com.example.tribecrypto.cryptoList

import com.example.tribecrypto.data.CryptoCurrencyEntity

interface CryptoListView {

    fun countOfCurrenciesReceived(value: Int)

    fun setList(data: List<CryptoCurrencyEntity>)
}
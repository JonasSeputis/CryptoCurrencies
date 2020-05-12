package com.example.tribecrypto.repository

import androidx.lifecycle.LiveData
import com.example.tribecrypto.data.CryptoCurrency
import com.example.tribecrypto.data.CryptoCurrencyCallObject
import io.reactivex.Single

interface CurrencyRepository {

    fun getAllCurrencies() : LiveData<CryptoCurrency>
    fun getCurrencyListFromApi(): Single<CryptoCurrencyCallObject>
}
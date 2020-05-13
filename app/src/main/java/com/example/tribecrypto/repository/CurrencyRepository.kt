package com.example.tribecrypto.repository

import com.example.tribecrypto.data.CryptoCurrencyEntity
import com.example.tribecrypto.data.networkObject.CryptoCurrencyCallObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyRepository {

    fun getAllCurrencies(): Observable<List<CryptoCurrencyEntity>>
    fun getCurrencyListFromApi(): Single<CryptoCurrencyCallObject>
    fun insertCurrenciesList(data: List<CryptoCurrencyEntity>): Completable
    fun updateCurrenciesList(data: List<CryptoCurrencyEntity>): Completable
    fun removeAllCurrencies(): Completable

}
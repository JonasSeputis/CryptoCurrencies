package com.example.tribecrypto.repository

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface CurrencyRepository {

    fun getAllCurrencies(): Observable<List<CryptoCurrencyEntity>>
    fun getEntityByName(name: String): Single<CryptoCurrencyEntity>
    fun getCurrencyListFromApi(): Single<CryptoCurrencyCallObject>
    fun insertCurrenciesList(data: List<CryptoCurrencyEntity>): Completable
    fun updateCurrency(data: CryptoCurrencyEntity): Completable
    fun updateCurrenciesList(data: List<CryptoCurrencyEntity>): Completable
    fun removeAllCurrencies(): Completable
}
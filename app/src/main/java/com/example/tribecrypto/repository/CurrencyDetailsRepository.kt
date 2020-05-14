package com.example.tribecrypto.repository

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface CurrencyDetailsRepository {

    fun getAllCurrenciesDetails(): Observable<List<CryptoCurrencyDetailsEntity>>
    fun insertDetailsList(data: List<CryptoCurrencyDetailsEntity>): Completable
    fun updateDetailsList(data: List<CryptoCurrencyDetailsEntity>): Completable
    fun removeAllDetails(): Completable
}
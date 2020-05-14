package com.example.tribecrypto.repository

import com.example.tribecrypto.data.entity.WatchListEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface WatchListRepository {

    fun getAllWatchList(): Observable<List<WatchListEntity>>
    fun insertWatchCurrency(data: WatchListEntity): Completable
    fun insertWatchCurrencies(data: List<WatchListEntity>): Completable
    fun updateWatchListCurrency(data: WatchListEntity): Completable
    fun updateWatchListCurrencies(data: List<WatchListEntity>): Completable
    fun removeWatchlistEntity(name: String): Completable
    fun removeAllWatchList(): Completable
}
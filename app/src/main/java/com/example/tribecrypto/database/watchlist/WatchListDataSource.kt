package com.example.tribecrypto.database.watchlist

import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.repository.WatchListRepository
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class WatchListDataSource @Inject constructor(private val watchDao: WatchListDao) :
    WatchListRepository {

    override fun getAllWatchList(): Observable<List<WatchListEntity>> {
        return watchDao.getAllWatchList()
    }

    override fun insertWatchCurrency(data: WatchListEntity): Completable {
        return Completable.fromAction { watchDao.insertEntity(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun insertWatchCurrencies(data: List<WatchListEntity>): Completable {
        return Completable.fromAction { watchDao.insertAll(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun updateWatchListCurrency(data: WatchListEntity): Completable {
        return Completable.fromAction { watchDao.updateWatchListCurrency(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun updateWatchListCurrencies(data: List<WatchListEntity>): Completable {
        return Completable.fromAction { watchDao.updateWatchListCurrencies(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun removeWatchlistEntity(name: String): Completable {
        return Completable.fromAction { watchDao.deleteEntityByName(name) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun removeAllWatchList(): Completable {
        return Completable.fromAction { watchDao.deleteAllWatchList() }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }
}
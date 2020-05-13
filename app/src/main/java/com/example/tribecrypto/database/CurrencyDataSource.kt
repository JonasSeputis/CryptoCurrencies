package com.example.tribecrypto.database

import com.example.tribecrypto.Api
import com.example.tribecrypto.data.CryptoCurrencyEntity
import com.example.tribecrypto.data.networkObject.CryptoCurrencyCallObject
import com.example.tribecrypto.repository.CurrencyRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CurrencyDataSource @Inject constructor(
    private val currencyDao: CurrencyDao,
    private val api: Api
) :
    CurrencyRepository {

    override fun getAllCurrencies(): Observable<List<CryptoCurrencyEntity>> {
        return currencyDao.getAllCurrencies()
    }

    override fun getCurrencyListFromApi(): Single<CryptoCurrencyCallObject> {
        return api.getLatestList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

    override fun insertCurrenciesList(data: List<CryptoCurrencyEntity>): Completable {
        return Completable.fromAction { currencyDao.insertAll(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun updateCurrenciesList(data: List<CryptoCurrencyEntity>): Completable {
        return Completable.fromAction { currencyDao.updateAll(data) }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun removeAllCurrencies(): Completable {
        return Completable.fromAction { currencyDao.deleteAllCryptoCurrencies() }
            .doOnError { t: Throwable? -> Timber.e(t) }
    }


}
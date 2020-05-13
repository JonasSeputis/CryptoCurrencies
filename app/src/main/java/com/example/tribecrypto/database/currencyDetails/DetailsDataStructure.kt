package com.example.tribecrypto.database.currencyDetails

import com.example.tribecrypto.data.CryptoCurrencyDetailsEntity
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import io.reactivex.Completable
import io.reactivex.Observable
import timber.log.Timber
import javax.inject.Inject

class DetailsDataStructure @Inject constructor(
    private val detailsDao: DetailsDao
) : CurrencyDetailsRepository {
    override fun getAllCurrenciesDetails(): Observable<List<CryptoCurrencyDetailsEntity>> {
        return detailsDao.getAllCryptoDetails()
    }

    override fun insertDetailsList(data: List<CryptoCurrencyDetailsEntity>): Completable {
        return Completable.fromAction{ detailsDao.insertAll(data)}
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun updateDetailsList(data: List<CryptoCurrencyDetailsEntity>): Completable {
        return Completable.fromAction{ detailsDao.updateAll(data)}
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

    override fun removeAllDetails(): Completable {
        return Completable.fromAction{ detailsDao.deleteAllCryptoCurrenciesDetails()}
            .doOnError { t: Throwable? -> Timber.e(t) }
    }

}
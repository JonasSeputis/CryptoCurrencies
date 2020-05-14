package com.example.tribecrypto.crypto_watch_list

import com.example.tribecrypto.base.BasePresenter
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.repository.WatchListRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CryptoWatchlistPresenter @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val watchListRepository: WatchListRepository
) : BasePresenter<CryptoWatchlistView>() {

    fun loadInfo() {
        getCurrenciesFromDB()
    }

    private fun getCurrenciesFromDB() {
        subscriptions.add(
            currencyRepository.getAllCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getWatchList(it)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun getWatchList(currencies: List<CryptoCurrencyEntity>) {
        subscriptions.add(
            watchListRepository.getAllWatchList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getWatchlistOfCurrencies(currencies, it)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun getWatchlistOfCurrencies(
        currencies: List<CryptoCurrencyEntity>,
        watchlist: List<WatchListEntity>
    ) {
        val watchlistCurrenciesList = mutableListOf<CryptoCurrencyEntity>()
        watchlist.forEach { it ->
            Timber.d(it.name)
            subscriptions.add(
                currencyRepository.getEntityByName(it.name)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        watchlistCurrenciesList.add(it)
                        if (watchlistCurrenciesList.size == watchlist.size) {
                            getView()?.provideCurrencyList(watchlistCurrenciesList)
                        }
                    }, { t: Throwable? ->
                        Timber.e(t)
                    })
            )
        }
    }
}
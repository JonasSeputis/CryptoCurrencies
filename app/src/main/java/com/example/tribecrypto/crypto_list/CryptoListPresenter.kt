package com.example.tribecrypto.crypto_list

import android.annotation.SuppressLint
import com.example.tribecrypto.base.BasePresenter
import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.repository.WatchListRepository
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CryptoListPresenter @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDetailsRepository: CurrencyDetailsRepository,
    private val watchListRepository: WatchListRepository
) : BasePresenter<CryptoListView>() {

    private var currencyWatchlistObject = CryptoCurrencyAndWatchlistObject(emptyList(), emptyList())

    fun getCurrenciesList(connectedToInternet: Boolean) {
        if (connectedToInternet) {
            getCurrenciesFromApi()
        } else {
            getView()?.noInternetConnectionInformation()
            getCurrenciesFromDB()
        }
    }

    private fun getCurrenciesFromApi() {
        subscriptions.add(
            currencyRepository.getCurrencyListFromApi()
                .subscribe({
                    val currenciesList = formCurrenciesList(it)
                    currencyWatchlistObject.currency = currenciesList.currenciesList
                    getWatchList()
                    setWatchListTest(currenciesList.currenciesList)
                    insertToDB(currenciesList.currenciesList)
                    insertDetailsToDB(currenciesList.currenciesDetailsList)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    @SuppressLint("CheckResult")
    fun getCurrenciesFromDB() {
        subscriptions.add(
            currencyRepository.getAllCurrencies()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currencyWatchlistObject.currency = it
                    getWatchList()
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun getWatchList() {
        subscriptions.add(
            watchListRepository.getAllWatchList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    currencyWatchlistObject.watchlist = it
                    getView()?.setEntityList(currencyWatchlistObject)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun insertToDB(data: List<CryptoCurrencyEntity>) {
        subscriptions.add(
            currencyRepository.removeAllCurrencies()
                .andThen(currencyRepository.insertCurrenciesList(data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    private fun insertDetailsToDB(data: List<CryptoCurrencyDetailsEntity>) {
        subscriptions.add(
            currencyDetailsRepository.removeAllDetails()
                .andThen(currencyDetailsRepository.insertDetailsList(data))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        )
    }

    private fun setWatchListTest(list: List<CryptoCurrencyEntity>) {
        val watchListList = mutableListOf<WatchListEntity>()
        val watchItem = WatchListEntity(list.first().name, 1)
        watchListList.add(watchItem)
        subscriptions.add(
            watchListRepository.insertWatchCurrencies(watchListList)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({}, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun formCurrenciesList(currency: CryptoCurrencyCallObject): CurrenciesLists {
        val cryptoCurrenciesEntityList = mutableListOf<CryptoCurrencyEntity>()
        val cryptoDetailsEntityList = mutableListOf<CryptoCurrencyDetailsEntity>()
        currency.cryptoCurrency.forEach {
            val detailsObject = it.cryptoQuote.cryptoCurrencyQuoteObject
            cryptoCurrenciesEntityList.add(
                CryptoCurrencyEntity(
                    it.name,
                    it.symbol,
                    it.slug,
                    it.circulatingSupply,
                    it.cmcRank,
                    it.dateAdded,
                    it.lastUpdated,
                    it.cryptoQuote.cryptoCurrencyQuoteObject.percentChange24h
                )
            )
            cryptoDetailsEntityList.add(
                CryptoCurrencyDetailsEntity(
                    it.name,
                    detailsObject.price,
                    detailsObject.percentChange1h,
                    detailsObject.percentChange7d,
                    detailsObject.lastUpdated
                )
            )
        }
        return CurrenciesLists(cryptoCurrenciesEntityList, cryptoDetailsEntityList)
    }

    fun addCurrencyToWatchList(currency: CryptoCurrencyEntity, watchList: List<WatchListEntity>) {
        val watchlistObjects = mutableListOf<WatchListEntity>()
        for (watch in watchList) {
            if (watch.name == currency.name) {
                watchlistObjects.add(watch)
            }
        }

        if (watchlistObjects.isEmpty()) {
            subscriptions.add(
                watchListRepository.insertWatchCurrency(WatchListEntity(currency.name, 1))
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getCurrenciesFromDB()
                    }, { t: Throwable? ->
                        Timber.e(t)
                    })
            )
        } else {
            subscriptions.add(
                watchListRepository.removeWatchlistEntity(currency.name)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        getCurrenciesFromDB()
                    }, { t: Throwable? ->
                        Timber.e(t)
                    })
            )
        }
    }
}

data class CurrenciesLists(
    val currenciesList: List<CryptoCurrencyEntity>,
    val currenciesDetailsList: List<CryptoCurrencyDetailsEntity>
)
package com.example.tribecrypto.cryptoList

import com.example.tribecrypto.base.BasePresenter
import com.example.tribecrypto.data.CryptoCurrencyDetailsEntity
import com.example.tribecrypto.data.CryptoCurrencyEntity
import com.example.tribecrypto.data.networkObject.CryptoCurrencyCallObject
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import com.example.tribecrypto.repository.CurrencyRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CryptoListPresenter @Inject constructor(
    private val currencyRepository: CurrencyRepository,
    private val currencyDetailsRepository: CurrencyDetailsRepository
) : BasePresenter<CryptoListView>() {

    fun test() {
        subscriptions.add(
            currencyRepository.getCurrencyListFromApi()
                .subscribe({
                    getView()?.countOfCurrenciesReceived(it.cryptoCurrency.size)
                    Timber.d("${it.cryptoCurrency.first().name}, ${it.cryptoCurrency.first().lastUpdated}, ${it.cryptoCurrency.first().cryptoQuote}")
                    Timber.d("${it.cryptoCurrency.first().cryptoQuote.cryptoCurrencyQuoteObject.price}, ${it.cryptoCurrency.first().cryptoQuote.cryptoCurrencyQuoteObject.percentChange24h}")
                    val currenciesList = formCurrenciesList(it)
                    setCryptoCurrenciesToDB(currenciesList.currenciesList)
                    setCryptoCurrenciesDetailsToDB(currenciesList.currenciesDetailsList)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun setCryptoCurrenciesToDB(list: List<CryptoCurrencyEntity>) {
        subscriptions.add(
            currencyRepository.insertCurrenciesList(list)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("SUCCESSFUL INSERTION TO DATABASE")
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }

    private fun setCryptoCurrenciesDetailsToDB(data: List<CryptoCurrencyDetailsEntity>) {
        subscriptions.add(
            currencyDetailsRepository.insertDetailsList(data)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    Timber.d("SUCCESSFUL INSERTION TO DATABASE")
                }, { t: Throwable? ->
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
                    it.lastUpdated
                )
            )
            cryptoDetailsEntityList.add(
                CryptoCurrencyDetailsEntity(
                    it.name,
                    detailsObject.price,
                    detailsObject.percentChange1h,
                    detailsObject.percentChange24h,
                    detailsObject.percentChange7d,
                    detailsObject.lastUpdated
                )
            )
        }
        return CurrenciesLists(cryptoCurrenciesEntityList, cryptoDetailsEntityList)
    }

    fun getAllItemsFromDatabase() {
        subscriptions.add(
            currencyRepository.getAllCurrencies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    getView()?.setList(it)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
        subscriptions.add(
            currencyDetailsRepository.getAllCurrenciesDetails()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    it.forEach {c -> Timber.d("${c.currencyName}, ${c.percentChange24h}") }
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }
}

data class CurrenciesLists(
    val currenciesList: List<CryptoCurrencyEntity>,
    val currenciesDetailsList: List<CryptoCurrencyDetailsEntity>
)
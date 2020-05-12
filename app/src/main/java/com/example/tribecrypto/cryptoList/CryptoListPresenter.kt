package com.example.tribecrypto.cryptoList

import com.example.tribecrypto.base.BasePresenter
import com.example.tribecrypto.repository.CurrencyRepository
import timber.log.Timber
import javax.inject.Inject

class CryptoListPresenter @Inject constructor(
    private val currencyRepository: CurrencyRepository
) : BasePresenter<CryptoListView>() {

    fun test() {
        subscriptions.add(
            currencyRepository.getCurrencyListFromApi()
                .subscribe({
                    getView()?.countOfCurrenciesReceived(it.cryptoCurrency.size)
                }, { t: Throwable? ->
                    Timber.e(t)
                })
        )
    }
}
package com.example.tribecrypto.crypto_list

import io.reactivex.Observable
import org.junit.Before

import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GetCurrenciesListTest {

    @Mock
    lateinit var currencyRepository: CurrencyRepository

    @Mock
    lateinit var currencyDetailsRepository: CurrencyDetailsRepository

    @Mock
    lateinit var watchListRepository: WatchListRepository

    @Mock
    lateinit var view: CryptoListView

    lateinit var presenter: CryptoListPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = CryptoListPresenter(currencyRepository, currencyDetailsRepository, watchListRepository)
    }

    @Test
    fun noInternetConnection() {
        //Mock
        val connectedToInternet = false
        val emptyList : List<CryptoCurrencyEntity> = emptyList()
        doReturn(Observable.just(emptyList)).`when`(currencyRepository).getAllCurrencies()
        //Act
        presenter.getCurrenciesList(connectedToInternet)
        //Assert
        verify(view).noInternetConnectionInformation()
    }
}
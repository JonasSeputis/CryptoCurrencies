package com.example.tribecrypto.crypto_list

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import com.example.tribecrypto.data.entity.WatchListEntity
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject
import com.example.tribecrypto.data.network_object.CryptoCurrencyObject
import com.example.tribecrypto.repository.CurrencyDetailsRepository
import com.example.tribecrypto.repository.CurrencyRepository
import com.example.tribecrypto.repository.WatchListRepository
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
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
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        presenter = CryptoListPresenter(currencyRepository, currencyDetailsRepository, watchListRepository)
        presenter.setView(view)
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

    @Test
    fun internetConnection() {
        //Mock
        val connectedToInternet = true
        val emptyList: List<CryptoCurrencyObject> = emptyList()
        val emptyObject = CryptoCurrencyCallObject(emptyList)
        val emptyWatchList : List<WatchListEntity> = emptyList()
        val emptyCryptoAndWatchObject = CryptoCurrencyAndWatchlistObject(emptyList(), emptyList())
        doReturn(Single.just(emptyObject)).`when`(currencyRepository).getCurrencyListFromApi()
        doReturn(Observable.just(emptyWatchList)).`when`(watchListRepository).getAllWatchList()
        //Act
        presenter.getCurrenciesList(connectedToInternet)
        //Assert
        verify(view).setEntityList(emptyCryptoAndWatchObject)
    }
}
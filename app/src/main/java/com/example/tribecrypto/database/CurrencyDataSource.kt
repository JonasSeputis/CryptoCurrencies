package com.example.tribecrypto.database

import androidx.lifecycle.LiveData
import com.example.tribecrypto.Api
import com.example.tribecrypto.data.CryptoCurrency
import com.example.tribecrypto.data.CryptoCurrencyCallObject
import com.example.tribecrypto.repository.CurrencyRepository
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class CurrencyDataSource @Inject constructor(private val currencyDao: CurrencyDao, private val api: Api) :
    CurrencyRepository {

    override fun getAllCurrencies(): LiveData<CryptoCurrency> {
        return currencyDao.getAllCurrencies()
    }

    override fun getCurrencyListFromApi(): Single<CryptoCurrencyCallObject> {
        return api.getLatestList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}
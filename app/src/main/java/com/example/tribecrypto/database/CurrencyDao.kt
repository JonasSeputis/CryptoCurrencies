package com.example.tribecrypto.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import com.example.tribecrypto.data.CryptoCurrency

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM crypto_currency")
    fun getAllCurrencies() : LiveData<CryptoCurrency>


}
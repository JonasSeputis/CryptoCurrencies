package com.example.tribecrypto.database

import androidx.room.*
import com.example.tribecrypto.data.CryptoCurrencyEntity
import io.reactivex.Observable

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM crypto_currency")
    fun getAllCurrencies() : Observable<List<CryptoCurrencyEntity>>

    @Update
    fun updateAll(data : List<CryptoCurrencyEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data : List<CryptoCurrencyEntity>)

    @Query("DELETE FROM crypto_currency")
    fun deleteAllCryptoCurrencies()

}
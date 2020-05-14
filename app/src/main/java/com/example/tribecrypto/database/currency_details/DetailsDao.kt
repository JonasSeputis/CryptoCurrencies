package com.example.tribecrypto.database.currency_details

import androidx.room.*
import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity
import io.reactivex.Observable

@Dao
interface DetailsDao {

    @Query("SELECT * FROM crypto_details")
    fun getAllCryptoDetails(): Observable<List<CryptoCurrencyDetailsEntity>>

    @Update
    fun updateAll(data: List<CryptoCurrencyDetailsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data: List<CryptoCurrencyDetailsEntity>)

    @Query("DELETE FROM crypto_details")
    fun deleteAllCryptoCurrenciesDetails()
}
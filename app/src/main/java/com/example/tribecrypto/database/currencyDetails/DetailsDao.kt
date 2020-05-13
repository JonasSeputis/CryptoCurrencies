package com.example.tribecrypto.database.currencyDetails

import androidx.room.*
import com.example.tribecrypto.data.CryptoCurrencyDetailsEntity
import io.reactivex.Observable

@Dao
interface DetailsDao {

    @Query("SELECT * FROM crypto_details")
    fun getAllCryptoDetails() : Observable<List<CryptoCurrencyDetailsEntity>>

    @Update
    fun updateAll(data : List<CryptoCurrencyDetailsEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data : List<CryptoCurrencyDetailsEntity>)

    @Query("DELETE FROM crypto_details")
    fun deleteAllCryptoCurrenciesDetails()


}
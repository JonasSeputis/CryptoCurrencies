package com.example.tribecrypto.database

import androidx.room.*
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity
import io.reactivex.Observable
import io.reactivex.Single

@Dao
interface CurrencyDao {

    @Query("SELECT * FROM crypto_currency")
    fun getAllCurrencies() : Observable<List<CryptoCurrencyEntity>>

    @Query("SELECT * FROM crypto_currency WHERE name LIKE :name")
    fun getEntityByName(name: String): Single<CryptoCurrencyEntity>

    @Update
    fun updateAll(data : List<CryptoCurrencyEntity>)

    @Update(entity = CryptoCurrencyEntity::class)
    fun update(date: CryptoCurrencyEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data : List<CryptoCurrencyEntity>)

    @Query("DELETE FROM crypto_currency")
    fun deleteAllCryptoCurrencies()
}
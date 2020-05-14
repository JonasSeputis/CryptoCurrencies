package com.example.tribecrypto.database.watchlist

import androidx.room.*
import com.example.tribecrypto.data.entity.WatchListEntity
import io.reactivex.Observable

@Dao
interface WatchListDao {

    @Query("SELECT * FROM watch_list")
    fun getAllWatchList(): Observable<List<WatchListEntity>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(data: List<WatchListEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertEntity(data: WatchListEntity)

    @Update
    fun updateWatchListCurrencies(data: List<WatchListEntity>)

    @Update
    fun updateWatchListCurrency(data: WatchListEntity)

    @Query("DELETE FROM watch_list WHERE name LIKE :name")
    fun deleteEntityByName(name: String)

    @Query("DELETE FROM watch_list")
    fun deleteAllWatchList()
}
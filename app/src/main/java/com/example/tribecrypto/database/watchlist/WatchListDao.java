package com.example.tribecrypto.database.watchlist;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tribecrypto.data.entity.WatchListEntity;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface WatchListDao {

    @Query("SELECT * FROM watch_list")
    Observable<List<WatchListEntity>> getAllWatchList();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<WatchListEntity> data);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertEntity(WatchListEntity data);

    @Update
    void updateWatchListCurrencies(List<WatchListEntity> data);

    @Update
    void updateWatchListCurrency(WatchListEntity data);

    @Query("DELETE FROM watch_list WHERE name LIKE :name")
    void deleteEntityByName(String name);

    @Query("DELETE FROM watch_list")
    void deleteAllWatchList();

}

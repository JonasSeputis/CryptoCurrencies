package com.example.tribecrypto.database.currency_details;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;

import java.util.List;

import io.reactivex.Observable;

@Dao
public interface DetailsDao {

    @Query("SELECT * FROM crypto_details")
    Observable<List<CryptoCurrencyDetailsEntity>> getAllCryptoDetails();

    @Update
    void updateAll(List<CryptoCurrencyDetailsEntity> data);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CryptoCurrencyDetailsEntity> data);

    @Query("DELETE FROM crypto_details")
    void deleteAllCryptoCurrenciesDetails();
}

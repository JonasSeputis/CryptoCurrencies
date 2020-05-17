package com.example.tribecrypto.database.currency;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

@Dao
public interface CurrencyDao {

    @Query("SELECT * FROM crypto_currency")
    Observable<List<CryptoCurrencyEntity>> getAllCurrencies();

    @Query("SELECT * FROM crypto_currency WHERE name LIKE :name")
    Single<CryptoCurrencyEntity> getEntityByName(String name);

    @Update
    void updateAll(List<CryptoCurrencyEntity> data);

    @Update(entity = CryptoCurrencyEntity.class)
    void update(CryptoCurrencyEntity date);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<CryptoCurrencyEntity> data);

    @Query("DELETE FROM crypto_currency")
    void deleteAllCryptoCurrencies();
}

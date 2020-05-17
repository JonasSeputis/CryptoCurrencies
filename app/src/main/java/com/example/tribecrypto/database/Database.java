package com.example.tribecrypto.database;

import androidx.room.RoomDatabase;

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.database.currency.CurrencyDao;
import com.example.tribecrypto.database.currency_details.DetailsDao;
import com.example.tribecrypto.database.watchlist.WatchListDao;

@androidx.room.Database(entities = {CryptoCurrencyEntity.class,
CryptoCurrencyDetailsEntity.class,
WatchListEntity.class},
        version = 1,
exportSchema = false)
public abstract class Database extends RoomDatabase {

    public abstract CurrencyDao getCurrencyDao();
    public abstract DetailsDao getCurrencyDetailsDao();
    public abstract WatchListDao getWatchListDao();
}

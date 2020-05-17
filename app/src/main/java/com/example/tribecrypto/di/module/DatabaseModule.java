package com.example.tribecrypto.di.module;

import android.app.Application;

import androidx.room.Room;

import com.example.tribecrypto.database.currency.CurrencyDao;
import com.example.tribecrypto.database.currency.CurrencyDataSource;
import com.example.tribecrypto.database.currency_details.DetailsDao;
import com.example.tribecrypto.database.currency_details.DetailsDataSource;
import com.example.tribecrypto.database.Database;
import com.example.tribecrypto.database.watchlist.WatchListDao;
import com.example.tribecrypto.database.watchlist.WatchListDataSource;
import com.example.tribecrypto.java.Api;
import com.example.tribecrypto.repository.CurrencyDetailsRepository;
import com.example.tribecrypto.repository.CurrencyRepository;
import com.example.tribecrypto.repository.WatchListRepository;

import dagger.Module;
import dagger.Provides;

@Module
public class DatabaseModule {


    private Database database;

    public DatabaseModule(Application application) {
        database = Room.databaseBuilder(application, Database.class, "database").build();
    }

    @Provides
    public Database providesDatabase() {
        return database;
    }

    @Provides
    public CurrencyDao providesCurrencyDao(Database database) {
        return database.getCurrencyDao();
    }

    @Provides
    public DetailsDao providesCurrencyDetailsDao(Database database){
        return database.getCurrencyDetailsDao();
    }

    @Provides
    public WatchListDao providesWatchListDao(Database database){
        return database.getWatchListDao();
    }

    @Provides
    public CurrencyRepository currencyRepository(CurrencyDao currencyDao, Api api){
        return new CurrencyDataSource(currencyDao, api);
    }

    @Provides
    public CurrencyDetailsRepository currencyDetailsRepository(DetailsDao currencyDetailsDao){
        return new DetailsDataSource(currencyDetailsDao);
    }

    @Provides
    public WatchListRepository watchListRepository(WatchListDao watchListDao){
        return new WatchListDataSource(watchListDao);
    }
}

package com.example.tribecrypto.di.component;

import android.app.Application;

import com.example.tribecrypto.database.currency.CurrencyDao;
import com.example.tribecrypto.database.currency_details.DetailsDao;
import com.example.tribecrypto.database.Database;
import com.example.tribecrypto.database.watchlist.WatchListDao;
import com.example.tribecrypto.di.module.AppModule;
import com.example.tribecrypto.di.module.DatabaseModule;
import com.example.tribecrypto.di.module.NetModule;
import com.example.tribecrypto.java.Api;
import com.example.tribecrypto.java.ApplicationClass;
import com.example.tribecrypto.repository.CurrencyDetailsRepository;
import com.example.tribecrypto.repository.CurrencyRepository;
import com.example.tribecrypto.repository.WatchListRepository;

import dagger.Component;

@Component(modules = {AppModule.class, NetModule.class, DatabaseModule.class})
public interface ApplicationComponent {

    void inject(ApplicationClass applicationClass);

    CurrencyDao currencyDao();
    DetailsDao detailsDao();
    WatchListDao watchListDao();
    CurrencyRepository currencyRepository();
    CurrencyDetailsRepository currencyDetailsRepository();
    WatchListRepository watchListRepository();
    Database database();
    Api api();
    Application application();
}

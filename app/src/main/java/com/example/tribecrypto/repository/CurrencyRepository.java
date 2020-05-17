package com.example.tribecrypto.repository;

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

public interface CurrencyRepository {

    Observable<List<CryptoCurrencyEntity>> getAllCurrencies();
    Single<CryptoCurrencyEntity> getEntityByName(String name);
    Single<CryptoCurrencyCallObject> getCurrencyListFromApi();
    Completable insertCurrenciesList(List<CryptoCurrencyEntity> data);
    Completable updateCurrency(CryptoCurrencyEntity data);
    Completable updateCurrenciesList(List<CryptoCurrencyEntity> data);
    Completable removeAllCurrencies();

}

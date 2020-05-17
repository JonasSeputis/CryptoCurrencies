package com.example.tribecrypto.database.currency;

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject;
import com.example.tribecrypto.java.Api;
import com.example.tribecrypto.repository.CurrencyRepository;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class CurrencyDataSource implements CurrencyRepository {

    private CurrencyDao dao;
    private Api api;

    public CurrencyDataSource(CurrencyDao dao, Api api) {
        this.dao = dao;
        this.api = api;
    }

    @Override
    public Observable<List<CryptoCurrencyEntity>> getAllCurrencies() {
        return dao.getAllCurrencies();
    }

    @Override
    public Single<CryptoCurrencyEntity> getEntityByName(String name) {
        return dao.getEntityByName(name);
    }

    @Override
    public Single<CryptoCurrencyCallObject> getCurrencyListFromApi() {
        return api.getLatestList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Completable insertCurrenciesList(List<CryptoCurrencyEntity> data) {
        return Completable.fromAction(() -> dao.insertAll(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable updateCurrency(CryptoCurrencyEntity data) {
        return Completable.fromAction(() -> dao.update(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable updateCurrenciesList(List<CryptoCurrencyEntity> data) {
        return Completable.fromAction(() -> dao.updateAll(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable removeAllCurrencies() {
        return Completable.fromAction(() -> dao.deleteAllCryptoCurrencies())
                .doOnError(Throwable::printStackTrace);
    }
}

package com.example.tribecrypto.database.currency_details;

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;
import com.example.tribecrypto.repository.CurrencyDetailsRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class DetailsDataSource implements CurrencyDetailsRepository {

    private DetailsDao dao;

    @Inject
    public DetailsDataSource(DetailsDao detailsDao) {
        this.dao = detailsDao;
    }

    @Override
    public Observable<List<CryptoCurrencyDetailsEntity>> getAllCurrenciesDetails() {
        return dao.getAllCryptoDetails();
    }

    @Override
    public Completable insertDetailsList(List<CryptoCurrencyDetailsEntity> data) {
        return Completable.fromAction(() -> dao.insertAll(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable updateDetailsList(List<CryptoCurrencyDetailsEntity> data) {
        return Completable.fromAction(() -> dao.updateAll(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable removeAllDetails() {
        return Completable.fromAction(() -> dao.deleteAllCryptoCurrenciesDetails())
                .doOnError(Throwable::printStackTrace);
    }
}

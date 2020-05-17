package com.example.tribecrypto.repository;

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface CurrencyDetailsRepository {

    Observable<List<CryptoCurrencyDetailsEntity>> getAllCurrenciesDetails();
    Completable insertDetailsList(List<CryptoCurrencyDetailsEntity> data);
    Completable updateDetailsList(List<CryptoCurrencyDetailsEntity> data);
    Completable removeAllDetails();
}

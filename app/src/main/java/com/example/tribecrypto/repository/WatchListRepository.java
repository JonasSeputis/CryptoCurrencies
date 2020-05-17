package com.example.tribecrypto.repository;

import com.example.tribecrypto.data.entity.WatchListEntity;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;

public interface WatchListRepository {

    Observable<List<WatchListEntity>> getAllWatchList();
    Completable insertWatchCurrency(WatchListEntity data);
    Completable insertWatchCurrencies(List<WatchListEntity> data);
    Completable updateWatchListCurrency(WatchListEntity data);
    Completable updateWatchListCurrencies(List<WatchListEntity> data);
    Completable removeWatchlistEntity(String name);
    Completable removeAllWatchList();
}

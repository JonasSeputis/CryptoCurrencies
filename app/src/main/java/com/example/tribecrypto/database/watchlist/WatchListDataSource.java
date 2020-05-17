package com.example.tribecrypto.database.watchlist;

import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.repository.WatchListRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;

public class WatchListDataSource implements WatchListRepository {

    private WatchListDao dao;

    @Inject
    public WatchListDataSource(WatchListDao dao) {
        this.dao = dao;
    }


    @Override
    public Observable<List<WatchListEntity>> getAllWatchList() {
        return dao.getAllWatchList();
    }

    @Override
    public Completable insertWatchCurrency(WatchListEntity data) {
        return Completable.fromAction(() -> dao.insertEntity(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable insertWatchCurrencies(List<WatchListEntity> data) {
        return Completable.fromAction(() -> dao.insertAll(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable updateWatchListCurrency(WatchListEntity data) {
        return Completable.fromAction(() -> dao.updateWatchListCurrency(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable updateWatchListCurrencies(List<WatchListEntity> data) {
        return Completable.fromAction(() -> dao.updateWatchListCurrencies(data))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable removeWatchlistEntity(String name) {
        return Completable.fromAction(() -> dao.deleteEntityByName(name))
                .doOnError(Throwable::printStackTrace);
    }

    @Override
    public Completable removeAllWatchList() {
        return Completable.fromAction(() -> dao.deleteAllWatchList())
                .doOnError(Throwable::printStackTrace);
    }
}

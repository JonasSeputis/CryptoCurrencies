package com.example.tribecrypto.crypto_watch_list;

import com.example.tribecrypto.base.BasePresenter;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.repository.CurrencyRepository;
import com.example.tribecrypto.repository.WatchListRepository;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CryptoWatchlistPresenter extends BasePresenter<CryptoWatchlistView> {

    private CurrencyRepository currencyRepository;
    private WatchListRepository watchlistRepository;

    @Inject
    public CryptoWatchlistPresenter(
            CurrencyRepository currencyRepository,
            WatchListRepository watchListRepository
    ) {
        this.currencyRepository = currencyRepository;
        this.watchlistRepository = watchListRepository;
    }

    public void loadInfo() {
        getCurrenciesFromDB();
    }

    private void getCurrenciesFromDB() {
        subscriptions.add(
                currencyRepository.getAllCurrencies()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(this::getWatchList)
                        .doOnError(Throwable::printStackTrace)
                        .subscribe()
        );
    }

    private void getWatchList(List<CryptoCurrencyEntity> currencies) {
        subscriptions.add(
                watchlistRepository.getAllWatchList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(it -> {
                            getWatchlistOfCurrencies(currencies, it);
                        })
                        .subscribe()

        );
    }

    private void getWatchlistOfCurrencies(
            List<CryptoCurrencyEntity> currencies,
            List<WatchListEntity> watchlist
    ) {
        List<CryptoCurrencyEntity> watchlistCurrenciesList = new ArrayList<>();
        watchlist.forEach(it -> {
            Timber.d(it.getName());
            subscriptions.add(
                    currencyRepository.getEntityByName(it.getName())
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnSuccess(currency -> {
                                watchlistCurrenciesList.add(currency);
                                if (watchlistCurrenciesList.size() == watchlist.size()) {
                                    getView().provideCurrencyList(watchlistCurrenciesList);
                                }
                            })
                            .subscribe()
            );
        });
    }
}

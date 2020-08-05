package com.example.tribecrypto.crypto_list;

import com.example.tribecrypto.base.BasePresenter;
import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.entity.WatchListEntity;
import com.example.tribecrypto.data.network_object.CryptoCurrencyCallObject;
import com.example.tribecrypto.data.network_object.CryptoCurrencyQuoteObject;
import com.example.tribecrypto.repository.CurrencyDetailsRepository;
import com.example.tribecrypto.repository.CurrencyRepository;
import com.example.tribecrypto.repository.WatchListRepository;
import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject;
import com.example.tribecrypto.utils.CurrenciesLists;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class CryptoListPresenter extends BasePresenter<CryptoListView> {

    private CurrencyRepository currencyRepository;
    private CurrencyDetailsRepository currencyDetailsRepository;
    private WatchListRepository watchListRepository;

    private CryptoCurrencyAndWatchlistObject currencyWatchlistObject = new CryptoCurrencyAndWatchlistObject(new ArrayList<>(), new ArrayList<>());

    @Inject
    public CryptoListPresenter(
            CurrencyRepository currencyRepository,
            CurrencyDetailsRepository currencyDetailsRepository,
            WatchListRepository watchListRepository
    ) {
        this.currencyRepository = currencyRepository;
        this.currencyDetailsRepository = currencyDetailsRepository;
        this.watchListRepository = watchListRepository;
    }

    public void getCurrenciesList(Boolean connectedToInternet) {
        Timber.d(connectedToInternet.toString());
        if (connectedToInternet) {
            getCurrenciesFromApi();
        } else {
            getView().noInternetConnectionInformation();
            getCurrenciesFromDB();
        }
    }

    public void getCurrenciesFromDB() {
        subscriptions.add(
                currencyRepository.getAllCurrencies()
                        .doOnNext(list -> {
                            currencyWatchlistObject.setCurrencies(list);
                            getWatchList();
                        })
                        .doOnError(Throwable::printStackTrace)
                        .subscribe()
        );
    }

    public void getCurrenciesFromApi() {
        subscriptions.add(
                currencyRepository.getCurrencyListFromApi()
                        .subscribe( it -> {
                            CurrenciesLists currenciesList = formCurrenciesList(it);
                            currencyWatchlistObject.getCurrencies().addAll(currenciesList.getCurrenciesList());
                            getWatchList();
                            insertToDB(currenciesList.getCurrenciesList());
                            insertDetailsToDB(currenciesList.getCurrenciesDetailsList());
                        }, Throwable::printStackTrace));
    }

    private void insertDetailsToDB(List<CryptoCurrencyDetailsEntity> currenciesDetailsList) {
        subscriptions.add(
                currencyDetailsRepository.removeAllDetails()
                        .andThen(currencyDetailsRepository.insertDetailsList(currenciesDetailsList))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    private void insertToDB(List<CryptoCurrencyEntity> currenciesList) {
        subscriptions.add(
                currencyRepository.removeAllCurrencies()
                        .andThen(currencyRepository.insertCurrenciesList(currenciesList))
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe()
        );
    }

    private void getWatchList() {
        subscriptions.add(
                watchListRepository.getAllWatchList()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(it -> {
                            currencyWatchlistObject.getWatchlist().addAll(it);
                            getView().setEntityList(currencyWatchlistObject);
                        })
                        .doOnError(Throwable::printStackTrace)
                        .subscribe());
    }

    private CurrenciesLists formCurrenciesList(CryptoCurrencyCallObject currency) {
        List<CryptoCurrencyEntity> cryptoCurrenciesEntityList = new ArrayList<>();
        List<CryptoCurrencyDetailsEntity> cryptoDetailsEntityList = new ArrayList<>();
        currency.getCryptoCurrencyObjects().forEach((it) -> {
                    CryptoCurrencyQuoteObject detailsObject = it.getCryptoQuote().getCryptoCurrencyQuoteObject();
                    cryptoCurrenciesEntityList.add(
                            new CryptoCurrencyEntity(
                                    it.getName(),
                                    it.getSymbol(),
                                    it.getSlug(),
                                    it.getCirculatingSupply(),
                                    it.getCmcRank(),
                                    it.getDateAdded(),
                                    it.getLastUpdated(),
                                    it.getCryptoQuote().getCryptoCurrencyQuoteObject().getPercentChange24h()
                            )
                    );
                    cryptoDetailsEntityList.add(
                            new CryptoCurrencyDetailsEntity(
                                    it.getName(),
                                    detailsObject.getPrice(),
                                    detailsObject.getPercentChange1h(),
                                    detailsObject.getPercentChange7d(),
                                    detailsObject.getLastUpdated()
                            )
                    );
                }
        );
        return new CurrenciesLists(cryptoCurrenciesEntityList, cryptoDetailsEntityList);
    }

    void addCurrencyToWatchList(CryptoCurrencyEntity currencyEntity, List<WatchListEntity> watchlist) {
        List<WatchListEntity> newWatchlist = new ArrayList<>();
        for (WatchListEntity watch : watchlist) {
            if (watch.getName().equals(currencyEntity.getName())) {
                newWatchlist.add(watch);
            }
        }

        if (newWatchlist.isEmpty()) {
            subscriptions.add(
                    watchListRepository.insertWatchCurrency(new WatchListEntity(currencyEntity.getName(), 1))
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(this::getCurrenciesFromDB)
                            .doOnError(Throwable::printStackTrace)
                            .subscribe()
            );
        } else {
            subscriptions.add(
                    watchListRepository.removeWatchlistEntity(currencyEntity.getName())
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .doOnComplete(this::getCurrenciesFromDB)
                            .doOnError(Throwable::printStackTrace)
                            .subscribe()
            );
        }
    }
}
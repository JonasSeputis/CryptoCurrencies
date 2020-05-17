package com.example.tribecrypto.utils;

import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;
import com.example.tribecrypto.data.entity.WatchListEntity;

import java.util.List;

public class CryptoCurrencyAndWatchlistObject {

    private List<CryptoCurrencyEntity> currencies;
    private List<WatchListEntity> watchlist;

    public CryptoCurrencyAndWatchlistObject(List<CryptoCurrencyEntity> currencies,
                                            List<WatchListEntity> watchlist) {
        this.currencies = currencies;
        this.watchlist = watchlist;
    }

    public void setCurrencies(List<CryptoCurrencyEntity> currencies) {
        this.currencies = currencies;
    }

    public void setWatchlist(List<WatchListEntity> watchlist) {
        this.watchlist = watchlist;
    }

    public List<CryptoCurrencyEntity> getCurrencies() {
        return currencies;
    }

    public List<WatchListEntity> getWatchlist() {
        return watchlist;
    }
}

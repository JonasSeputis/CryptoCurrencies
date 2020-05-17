package com.example.tribecrypto.utils;

import com.example.tribecrypto.data.entity.CryptoCurrencyDetailsEntity;
import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;

import java.util.List;

public class CurrenciesLists {

    private List<CryptoCurrencyEntity> currenciesList;
    private List<CryptoCurrencyDetailsEntity> currenciesDetailsList;

    public CurrenciesLists(List<CryptoCurrencyEntity> currenciesList,
                           List<CryptoCurrencyDetailsEntity> currenciesDetailsList) {
        this.currenciesList = currenciesList;
        this.currenciesDetailsList = currenciesDetailsList;
    }

    public List<CryptoCurrencyEntity> getCurrenciesList() {
        return currenciesList;
    }

    public List<CryptoCurrencyDetailsEntity> getCurrenciesDetailsList() {
        return currenciesDetailsList;
    }
}

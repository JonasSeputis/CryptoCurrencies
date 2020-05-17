package com.example.tribecrypto.crypto_list;


import com.example.tribecrypto.utils.CryptoCurrencyAndWatchlistObject;

public interface CryptoListView {
    void setEntityList(CryptoCurrencyAndWatchlistObject data);

    void noInternetConnectionInformation();
}

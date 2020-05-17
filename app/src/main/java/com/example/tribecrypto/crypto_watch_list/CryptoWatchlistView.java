package com.example.tribecrypto.crypto_watch_list;


import com.example.tribecrypto.data.entity.CryptoCurrencyEntity;

import java.util.List;

public interface CryptoWatchlistView {

    void provideCurrencyList(List<CryptoCurrencyEntity> data);
}

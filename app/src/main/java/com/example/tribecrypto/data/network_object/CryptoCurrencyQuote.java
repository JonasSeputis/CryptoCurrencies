package com.example.tribecrypto.data.network_object;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrencyQuote {
    @SerializedName("USD")
    private CryptoCurrencyQuoteObject cryptoCurrencyQuoteObject;

    public CryptoCurrencyQuoteObject getCryptoCurrencyQuoteObject() {
        return cryptoCurrencyQuoteObject;
    }
}

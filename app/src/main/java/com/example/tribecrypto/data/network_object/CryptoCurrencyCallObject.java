package com.example.tribecrypto.data.network_object;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CryptoCurrencyCallObject {
    @SerializedName("data")
    private List<CryptoCurrencyObject> cryptoCurrencyObjects;

    public List<CryptoCurrencyObject> getCryptoCurrencyObjects() {
        return cryptoCurrencyObjects;
    }
}

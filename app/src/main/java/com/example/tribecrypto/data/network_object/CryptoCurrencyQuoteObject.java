package com.example.tribecrypto.data.network_object;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrencyQuoteObject {
    @SerializedName("price")
    private String price;
    
    @SerializedName("percent_change_1h")
    private String percentChange1h;
    
    @SerializedName("percent_change_24h")
    private String percentChange24h;
    
    @SerializedName("percent_change_7d")
    private String percentChange7d;
    
    @SerializedName("last_updated")
    private String lastUpdated;

    public String getPrice() {
        return price;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public String getPercentChange24h() {
        return percentChange24h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}

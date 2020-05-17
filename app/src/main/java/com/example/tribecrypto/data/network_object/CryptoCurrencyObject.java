package com.example.tribecrypto.data.network_object;

import com.google.gson.annotations.SerializedName;

public class CryptoCurrencyObject {
    
    @SerializedName("name") 
    private String name;
    
    @SerializedName("symbol")
    private String symbol;
    
    @SerializedName("slug")
    private String slug;

    @SerializedName("circulating_supply")
    private String circulatingSupply;

    @SerializedName("cmc_rank")
    private Long cmcRank;

    @SerializedName("date_added")
    private String dateAdded;

    @SerializedName("last_updated")
    private String lastUpdated;

    @SerializedName("quote")
    private CryptoCurrencyQuote cryptoQuote;

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getSlug() {
        return slug;
    }

    public String getCirculatingSupply() {
        return circulatingSupply;
    }

    public Long getCmcRank() {
        return cmcRank;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public CryptoCurrencyQuote getCryptoQuote() {
        return cryptoQuote;
    }
}

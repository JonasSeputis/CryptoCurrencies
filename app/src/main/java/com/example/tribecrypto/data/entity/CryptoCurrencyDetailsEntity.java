package com.example.tribecrypto.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "crypto_details")
public class CryptoCurrencyDetailsEntity {

    public CryptoCurrencyDetailsEntity(@NotNull String currencyName,
                                       String price,
                                       String percentChange1h,
                                       String percentChange7d,
                                       String lastUpdated) {
        this.currencyName = currencyName;
        this.price = price;
        this.percentChange1h = percentChange1h;
        this.percentChange7d = percentChange7d;
        this.lastUpdated = lastUpdated;
    }

    @PrimaryKey
    @ColumnInfo(name = "currency_name")
    @NonNull
    private String currencyName;

    @ColumnInfo(name = "price")
    private String price;

    @ColumnInfo(name = "percent_change_1h")
    private String percentChange1h;

    @ColumnInfo(name = "percent_change_7d")
    private String percentChange7d;

    @ColumnInfo(name = "last_updated")
    private String lastUpdated;

    @NotNull
    public String getCurrencyName() {
        return currencyName;
    }

    public String getPrice() {
        return price;
    }

    public String getPercentChange1h() {
        return percentChange1h;
    }

    public String getPercentChange7d() {
        return percentChange7d;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }
}

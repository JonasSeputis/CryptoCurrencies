package com.example.tribecrypto.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "crypto_currency")
public class CryptoCurrencyEntity {

    public CryptoCurrencyEntity(
            @NotNull String name,
            String symbol,
            String slug,
            String circulatingSupply,
            Long cmcRank,
            String dateAdded,
            String lastUpdated,
            String percentChange24h
    ){
        this.name = name;
        this.symbol = symbol;
        this.slug = slug;
        this.circulatingSupply = circulatingSupply;
        this.cmcRank = cmcRank;
        this.dateAdded = dateAdded;
        this.lastUpdated = lastUpdated;
        this.percentChange24h = percentChange24h;
    }

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    @ColumnInfo(name = "symbol")
    private String symbol;

    @ColumnInfo(name = "slug")
    private String slug;

    @ColumnInfo(name = "circulating_syply")
    private String circulatingSupply;

    @ColumnInfo(name = "cmc_rank")
    private Long cmcRank;

    @ColumnInfo(name = "date_added")
    private String dateAdded;

    @ColumnInfo(name = "last_updated")
    private String lastUpdated;

    @ColumnInfo(name = "percent_change_24h")
    private String percentChange24h;

    @NotNull
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

    public String getPercentChange24h() {
        return percentChange24h;
    }
}

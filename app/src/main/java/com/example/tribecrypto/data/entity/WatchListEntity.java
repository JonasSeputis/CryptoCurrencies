package com.example.tribecrypto.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "watch_list")
public class WatchListEntity {

    public WatchListEntity(
            @NonNull String name,
            int watching
    ) {
        this.name = name;
        this.watching = watching;
    }

    @PrimaryKey
    @ColumnInfo(name = "name")
    @NonNull
    private String name;

    /**
     *  @watching value inside database is used as Boolean regards if it is included to watch list
     *  0 - Currency is not added to watch list
     *  1 - Currency is added to watch list
     */

    @ColumnInfo(name = "watching", defaultValue = "0")
    private int watching;

    @NotNull
    public String getName() {
        return name;
    }

    public int getWatching() {
        return watching;
    }
}

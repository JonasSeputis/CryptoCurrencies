package com.example.tribecrypto.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "watch_list")
data class WatchListEntity (
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    /**
     *  @watching value inside database is used as Boolean regards if it is included to watch list
     *  0 - Currency is not added to watch list
     *  1 - Currency is added to watch list
     */
    @ColumnInfo(name = "watching") val watching: Int = 0
)
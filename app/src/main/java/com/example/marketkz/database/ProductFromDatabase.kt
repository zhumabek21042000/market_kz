package com.example.marketkz.database

//import androidx.fragment.app.Fragment
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProductFromDatabase(
    @PrimaryKey(autoGenerate = true) val uid: Int?,
    @ColumnInfo val title: String,
    @ColumnInfo val photo_url: String,
    @ColumnInfo val price: Double
)
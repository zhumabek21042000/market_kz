package com.example.marketkz.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("name")
    val title: String,
    @SerializedName("photo_url")
    val photo_url: String,
    val price: Double,
    val isOnSale: Boolean
    ) {
}
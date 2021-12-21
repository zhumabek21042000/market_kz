package com.example.marketkz.repository

import com.example.marketkz.model.Product
import com.google.gson.Gson
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Single
import java.net.URL

class ProductsRepository {
    fun getAllProducts(): @NonNull Single<List<Product>> {
        return Single.create<List<Product>>{
            val json = URL("http://finepointmobile.com/data/prdoucts.json").readText()
            val products = Gson().fromJson(json, Array<Product>::class.java).toList()
            it.onSuccess(products)
        }
    }
    fun searchForProduct(term: String){

    }

    fun getProductPhotos(){

    }
}

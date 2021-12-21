package com.example.marketkz.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ProductDao {
    @Query("SELECT * FROM ProductFromDatabase")
    fun getAll(): List<ProductFromDatabase>

    @Insert
    fun insertAll(vararg products: ProductFromDatabase)

    @Query("SELECT * FROM ProductFromDatabase WHERE title LIKE :term")
    fun searchFor(term: String): List<ProductFromDatabase>

    @Query("DELETE FROM ProductFromDatabase")
    fun deleteALl()

    @Query("DELETE FROM ProductFromDatabase WHERE uid = :id")
    fun deleteProduct(id: Int)

    @Query("UPDATE ProductFromDatabase SET title = :new_title, photo_url = :new_photo, price = :new_price")
    fun updateProduct(new_title: String, new_photo: String, new_price: Double)
}
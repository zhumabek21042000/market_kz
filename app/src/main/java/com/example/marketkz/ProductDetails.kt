package com.example.marketkz

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.marketkz.database.AppDatabase
import com.example.marketkz.model.Product
import com.squareup.picasso.Picasso
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.net.URL
import java.nio.file.Files.find


class ProductDetails: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.product_details)

        val title = intent.getStringExtra("title")
        val photoUrl = intent.getStringExtra("photo_url")
        val product_name:TextView = findViewById(R.id.product_name)
        product_name.text = title
        val picture_view: ImageView = findViewById(R.id.product_photo)
        Log.d("Details", "$photoUrl")
        Picasso.get().load(photoUrl.toString()).into(picture_view)
        val price = intent.getDoubleExtra("price", 0.0)
        val price_view = findViewById<TextView>(R.id.priceDetail)
        price_view.text = "$price$"
//        Toast.makeText(this, "You clicked on $title", Toast.LENGTH_SHORT).show()

        findViewById<Button>(R.id.addToCartButton).setOnClickListener {

        }
        findViewById<Button>(R.id.availability).setOnClickListener {
            AlertDialog.Builder(this)
                .setMessage("$title is in stock!")
                .setPositiveButton("OK") { p0, p1 ->

                }
                .create()
                .show()
        }
        findViewById<Button>(R.id.editButton).setOnClickListener{
            val intent = Intent(this, EditProductActivity::class.java)
            intent.putExtra("title", title.toString())
            intent.putExtra("photo_url", photoUrl.toString())
            intent.putExtra("price", price)
            startActivity(intent)
        }
        findViewById<Button>(R.id.delete).setOnClickListener{
//            doAsync {
//
//                val db = Room.databaseBuilder(
//                    applicationContext,
//                    AppDatabase::class.java, "marketdb"
//                ).build()
//                val productsFromDatabase = db.productDao().searchFor("%$searching%")
//                val products = productsFromDatabase.map {
//                    Product(
//                        it.title,
//                        it.photo_url,
//                        it.price,
//                        true
//                    )
//                }
//                uiThread {
//                   db.productDao().deleteProduct()
//                }
//            }
        }
    }
}
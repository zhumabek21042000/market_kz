package com.example.marketkz

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.room.Room
import com.example.marketkz.database.AppDatabase
import com.example.marketkz.database.ProductFromDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.uiThread

class AdminFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_admin, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.submitButton).setOnClickListener{

            val title = find<TextView>(R.id.productTitle)
            val titleView = title.text

            val photo = find<TextView>(R.id.productPhotoUrl)
            val photoView = photo.text

            val price = find<TextView>(R.id.productPrice)
            val priceView = price.text
            doAsync {

                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "marketdb"
                ).build()
                db.productDao().insertAll(ProductFromDatabase(null, titleView.toString(), photoView.toString(), priceView.toString().toDouble()))
//                db.productDao().deleteALl()

                uiThread {
                    Log.d("check", "home")
                }
            }
        }
    }
}
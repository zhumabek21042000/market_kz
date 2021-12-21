package com.example.marketkz

//import android.app.Fragment
import android.os.Bundle
import android.util.Log
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.marketkz.database.AppDatabase
import com.example.marketkz.database.ProductFromDatabase
import com.example.marketkz.model.Product
import com.example.marketkz.repository.ProductsRepository
import com.google.gson.Gson
import io.reactivex.rxjava3.schedulers.Schedulers
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.find
import org.jetbrains.anko.support.v4.uiThread
import org.jetbrains.anko.uiThread
import java.net.URL

class MainFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        doAsync {
            val db = Room.databaseBuilder(
                activity!!.applicationContext,
                AppDatabase::class.java, "marketdb"
            ).build()

            val productsFromDatabase = db.productDao().getAll()
//            searchFor("%Apple%")

            val products = productsFromDatabase.map {
                Product(
                    it.title,
                    it.photo_url,
                    it.price,
                    true
                )


            }
            uiThread {
                find<RecyclerView>(R.id.recycler_view).apply {
                    layoutManager = GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(products)

                }
                find<ProgressBar>(R.id.progressBar).visibility = View.GONE
            }
        }
//        root.findViewById<ProgressBar>(R.id.progressBar).visibility = View.GONE
        val categories = listOf("Food", "Working tools", "Electronic", "Clothes", "Garden", "Kitchen")
        root.findViewById<RecyclerView>(R.id.categoriesRecyclerView).apply {
            layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            adapter = CategoriesAdapter(categories)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ProductsRepository().getAllProducts()
            .subscribeOn(Schedulers.io())
            .subscribe({
                find<RecyclerView>(R.id.recycler_view).apply{
                    layoutManager = androidx.recyclerview.widget.GridLayoutManager(activity, 2)
                    adapter = ProductsAdapter(it)
                }
                d("main", "success")
            }, {
                d("main", "error")
            })
        val searchButton: Button = find(R.id.searchButton)
        val searchItem: TextView = find(R.id.searchTerm)
        val searching = searchItem.text
        searchButton.setOnClickListener {
            doAsync {

                val db = Room.databaseBuilder(
                    activity!!.applicationContext,
                    AppDatabase::class.java, "marketdb"
                ).build()
                val productsFromDatabase = db.productDao().searchFor("%$searching%")
                val products = productsFromDatabase.map {
                    Product(
                        it.title,
                        it.photo_url,
                        it.price,
                        true
                    )
                }
                uiThread {
                    find<RecyclerView>(R.id.recycler_view).apply {
                        layoutManager = GridLayoutManager(activity, 2)
                        adapter = ProductsAdapter(products)

                    }
                    find<ProgressBar>(R.id.progressBar).visibility = View.GONE
                }
            }
        }


    }
}
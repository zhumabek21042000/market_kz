package com.example.marketkz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.marketkz.cart.CartActivity
import com.example.marketkz.database.AppDatabase
import com.example.marketkz.database.ProductFromDatabase
import com.example.marketkz.model.Product
import com.google.android.material.navigation.NavigationView
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        doAsync {

            val db = Room.databaseBuilder(
                applicationContext,
                AppDatabase::class.java, "marketdb"
            ).build()
//            db.productDao().insertAll(ProductFromDatabase(null, "Food- apple", 1.99))
            val products = db.productDao().getAll()
            uiThread {
                d("check", "product size? ${products.size} ")
            }
        }
        supportFragmentManager.beginTransaction().replace(R.id.frameLayout, MainFragment()).commit()
        val navigationView:NavigationView = findViewById(R.id.navigation)
        navigationView.setNavigationItemSelectedListener {
            when (it.itemId){
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MainFragment())
                        .commit()
                }
                R.id.actionAdmin -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, AdminFragment())
                        .commit()
                }
            }
            it.isChecked = true
            val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
            drawerLayout.closeDrawers()
            true
        }
        supportActionBar?.apply{
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_baseline_menu_24)
        }

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_toolbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.actionCart){
            startActivity(Intent(this, CartActivity::class.java))
        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawerLayout)
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }


}
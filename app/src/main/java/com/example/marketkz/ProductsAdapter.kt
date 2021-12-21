package com.example.marketkz

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.marketkz.model.Product
import com.squareup.picasso.Picasso


class ProductsAdapter(private val products: List<Product>) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.photo)
        val title: TextView = itemView.findViewById(R.id.title)
        val price: TextView = itemView.findViewById(R.id.price)
        val saleImageView:ImageView = itemView.findViewById(R.id.saleImageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_row, parent, false)
        val holder = ViewHolder(view)
        view.setOnClickListener{
            val intent = Intent(parent.context, ProductDetails::class.java)
            intent.putExtra("title", products[holder.adapterPosition].title)
            intent.putExtra("photo_url", products[holder.adapterPosition].photo_url)
            intent.putExtra("price", products[holder.adapterPosition].price)
            parent.context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        Picasso.get().load(products[position].photo_url).into(holder.image)
        holder.title.text = products[position].title
        val priceWithSign = products[position].price.toString() + "$"
        holder.price.text = priceWithSign
        if(product.isOnSale){
            holder.saleImageView.visibility = View.VISIBLE
        }
        else{
            holder.saleImageView.visibility = View.GONE
        }

    }

    override fun getItemCount() = products.size
}
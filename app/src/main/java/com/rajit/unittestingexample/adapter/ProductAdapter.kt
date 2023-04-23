package com.rajit.unittestingexample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rajit.unittestingexample.R
import com.rajit.unittestingexample.model.ProductListItem

class ProductAdapter(
    private val productList: List<ProductListItem>
): RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val currentProduct = productList[position]
        holder.name.text = currentProduct.title
        Glide.with(holder.image.context).load(currentProduct.image).into(holder.image)
    }

    class ProductViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
       val name = itemView.findViewById<TextView>(R.id.name)
       val image = itemView.findViewById<ImageView>(R.id.image)
    }

}
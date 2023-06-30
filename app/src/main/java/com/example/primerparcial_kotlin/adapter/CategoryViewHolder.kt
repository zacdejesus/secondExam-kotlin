package com.example.primerparcial_kotlin.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.primerparcial_kotlin.activity.CategoryJokeActivity
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.databinding.ItemCategoryBinding

class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val stundentNameTitle: TextView = view.findViewById<TextView>(R.id.textViewCategory)
    private val binding = ItemCategoryBinding.bind(view)

    fun render(category: String) {
        stundentNameTitle.text = category

        stundentNameTitle.setOnClickListener {
            handleNavigation(category)
        }
    }
    fun handleNavigation(category: String) {
        val bundle = Bundle()
        bundle.putString("category", category)
        val intent = Intent(binding.imageViewList.context, CategoryJokeActivity::class.java)
        intent.putExtras(bundle)
        binding.imageViewList.context.startActivity(intent)
    }
}
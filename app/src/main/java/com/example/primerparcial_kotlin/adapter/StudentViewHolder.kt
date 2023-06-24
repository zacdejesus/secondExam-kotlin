package com.example.primerparcial_kotlin.adapter

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.primerparcial_kotlin.activity.DetailActivity
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.databinding.ItemStudentBinding

class StudentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val stundentNameTitle: TextView = view.findViewById<TextView>(R.id.textViewNameStudent)
    private val binding = ItemStudentBinding.bind(view)

    fun render(category: String) {
        stundentNameTitle.text = category

        stundentNameTitle.setOnClickListener {
            handleNavigation(category)
        }

    }
    fun handleNavigation(student: String) {
        val bundle = Bundle()
        bundle.putString("category", student)
        val intent = Intent(binding.imageViewList.context, DetailActivity::class.java)
        intent.putExtras(bundle)
        binding.imageViewList.context.startActivity(intent)
    }
}
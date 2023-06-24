package com.example.primerparcial_kotlin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.primerparcial_kotlin.R

class StudentAdapter(private val categories: List<String>) : RecyclerView.Adapter<StudentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return StudentViewHolder(layoutInflater.inflate(R.layout.item_student, parent, false))
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        val item = categories[position]
        holder.render(item)
    }
}
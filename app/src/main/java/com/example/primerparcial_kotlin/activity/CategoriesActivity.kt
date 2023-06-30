package com.example.primerparcial_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primerparcial_kotlin.adapter.CategoryAdapter
import com.example.primerparcial_kotlin.databinding.ActivityCategoriesBinding
import com.example.primerparcial_kotlin.interfaces.CategoryActivityInterface
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.services.ApiService
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.primerparcial_kotlin.services.Retrofit
import com.example.primerparcial_kotlin.viewModels.CategoriesViewModel
import com.example.primerparcial_kotlin.viewModels.RandomJokeViewModel

class CategoriesActivity : AppCompatActivity(), CategoryActivityInterface {

    private lateinit var adapter: CategoryAdapter
    private  lateinit var binding: ActivityCategoriesBinding
    private lateinit var viewModel: CategoriesViewModel

    // LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerCategory.layoutManager = LinearLayoutManager(this)

        showRandomJokeActivity()
        viewModel = ViewModelProvider(this)[CategoriesViewModel::class.java]
        viewModel.listener = this
        viewModel.getCategories()
    }

    private fun showRandomJokeActivity() {
        val intent = Intent(this, RandomJokeActivity::class.java)
        startActivity(intent)
    }

    override fun onSuccess(response: ArrayList<String>) {
        runOnUiThread {
            adapter = CategoryAdapter(response)
            binding.recyclerCategory.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }
    override fun onError() {
        runOnUiThread {
            Toast.makeText(this@CategoriesActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
        }
    }
}
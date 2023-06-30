package com.example.primerparcial_kotlin.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.interfaces.JokeActivityInterface
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import com.example.primerparcial_kotlin.viewModels.CategoryJokeViewModel
import com.example.primerparcial_kotlin.viewModels.RandomJokeViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryJokeActivity : AppCompatActivity(), JokeActivityInterface {

    private lateinit var category: String
    private lateinit var viewModel: CategoryJokeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoryjoke)

        val buttonBack = findViewById<Button>(R.id.backButton)

        buttonBack.setOnClickListener {
            finish()
        }

        val receivedBundle = intent.extras
        if (receivedBundle != null) {
            val category = receivedBundle.getString("category")

            if (category != null) {
                viewModel = ViewModelProvider(this).get(CategoryJokeViewModel::class.java)
                viewModel.listener = this
                viewModel.getJokeByCategory(category)
            }
        }
    }

    override fun onSuccess(response: Joke) {
        runOnUiThread {
            val categoryJoke = findViewById<TextView>(R.id.categoryJoke)
            val categoryLabel = findViewById<TextView>(R.id.categoryLabel)
            categoryLabel.text = "Category: $category"
            categoryJoke.text = response.value
        }
    }

    override fun onError() {
        Toast.makeText(this@CategoryJokeActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
    }
}
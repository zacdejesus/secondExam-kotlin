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
import com.example.primerparcial_kotlin.viewModels.CategoryJokeViewModel


class CategoryJokeActivity : AppCompatActivity(), JokeActivityInterface {

    private lateinit var category: String
    private lateinit var viewModel: CategoryJokeViewModel
    private lateinit var categoryJoke: TextView
    private lateinit var categoryLabel: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categoryjoke)
        val receivedBundle = intent.extras
        val buttonBack = findViewById<Button>(R.id.backButton)
        categoryJoke = findViewById<TextView>(R.id.categoryJoke)
        categoryLabel = findViewById<TextView>(R.id.categoryLabel)
        category = receivedBundle?.getString("category").toString()

        viewModel = ViewModelProvider(this)[CategoryJokeViewModel::class.java]
        viewModel.listener = this

        buttonBack.setOnClickListener {
            finish()
        }

        if (receivedBundle != null) {
            if (category != null) {
                viewModel.getJokeByCategory(category)
            }
        }
    }

    override fun onSuccess(response: Joke) {
        runOnUiThread {
            categoryLabel.text = "Category: $category"
            categoryJoke.text = response.value
        }
    }

    override fun onError() {
        Toast.makeText(this@CategoryJokeActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
    }
}
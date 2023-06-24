package com.example.primerparcial_kotlin.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.adapter.StudentAdapter
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import com.example.primerparcial_kotlin.viewModels.StudentsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    private val studentsManager = StudentsManager()
    private lateinit var category: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val buttonBack = findViewById<Button>(R.id.backButton)

        buttonBack.setOnClickListener {
            finish()
        }

        val receivedBundle = intent.extras
        if (receivedBundle != null) {
            val category = receivedBundle.getString("category")

            if (category != null) {
                getJokeByCategory(category)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun getJokeByCategory(category: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getJoke("jokes/random?category=${category}")
            val response = call.body()

            runOnUiThread {
                // perform UI actions
                if (call.isSuccessful && response != null) {
                    val categoryJoke = findViewById<TextView>(R.id.categoryJoke)
                    val categoryLabel = findViewById<TextView>(R.id.categoryLabel)
                    categoryLabel.text = "Categoria: $category"
                    categoryJoke.text = response.value
                } else {
                    Toast.makeText(this@DetailActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
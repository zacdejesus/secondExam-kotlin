package com.example.primerparcial_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import com.example.primerparcial_kotlin.viewModels.StudentsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    private val studentsManager = StudentsManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val jokeText = findViewById<TextView>(R.id.textViewJoke)
        val buttonNavigate = findViewById<Button>(R.id.buttonNavigate)


        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getJoke("jokes/random")
            val response = call.body()

            runOnUiThread {
                if (call.isSuccessful) {
                    val joke = response?.value
                    jokeText.text = joke
                } else {
                    Toast.makeText(this@SecondActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            }
        }

        buttonNavigate.setOnClickListener {
            onButtonClick()
        }
    }

    private fun onButtonClick() {
        finish()
    }

}
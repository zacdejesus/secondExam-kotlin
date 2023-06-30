package com.example.primerparcial_kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.interfaces.JokeActivityInterface
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.viewModels.RandomJokeViewModel

class RandomJokeActivity : AppCompatActivity(), JokeActivityInterface {

    private lateinit var viewModel: RandomJokeViewModel
    private lateinit var jokeText: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_randomjoke)

        jokeText = findViewById<TextView>(R.id.textViewJoke)
        val buttonNavigate = findViewById<Button>(R.id.buttonNavigate)

        viewModel = ViewModelProvider(this).get(RandomJokeViewModel::class.java)
        viewModel.listener = this
        viewModel.getRandomJokes()

        buttonNavigate.setOnClickListener {
            onButtonClick()
        }
    }

    private fun onButtonClick() {
        finish()
    }

    override fun onSuccess(response: Joke) {
        runOnUiThread {
            val joke = response?.value
            jokeText.text = joke
        }
    }
    override fun onError() {
        runOnUiThread {
            Toast.makeText(this@RandomJokeActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
        }
    }
}
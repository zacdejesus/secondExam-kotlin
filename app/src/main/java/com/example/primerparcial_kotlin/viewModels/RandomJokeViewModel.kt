package com.example.primerparcial_kotlin.viewModels

import androidx.lifecycle.ViewModel
import com.example.primerparcial_kotlin.interfaces.JokeActivityInterface
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RandomJokeViewModel: ViewModel()  {

    var listener: JokeActivityInterface? = null

    fun getRandomJokes() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getJoke("jokes/random")
            val response = call.body()

                if (call.isSuccessful && response != null) {
                    listener?.onSuccess(response)
                } else {
                    listener?.onError()
                }
        }
    }

}
package com.example.primerparcial_kotlin.viewModels

import androidx.lifecycle.ViewModel
import com.example.primerparcial_kotlin.interfaces.JokeActivityInterface
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoryJokeViewModel: ViewModel() {

    var listener: JokeActivityInterface? = null

    fun getJokeByCategory(category: String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getJoke("jokes/random?category=${category}")
            val response = call.body()
                // perform UI actions
                if (call.isSuccessful && response != null) {
                    listener?.onSuccess(response)
                } else {
                    listener?.onError()
                }
        }
    }
}
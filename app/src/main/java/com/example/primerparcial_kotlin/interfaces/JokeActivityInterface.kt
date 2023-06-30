package com.example.primerparcial_kotlin.interfaces
import com.example.primerparcial_kotlin.models.Joke

interface JokeActivityInterface {
    fun onSuccess(response: Joke)
    fun onError()
}
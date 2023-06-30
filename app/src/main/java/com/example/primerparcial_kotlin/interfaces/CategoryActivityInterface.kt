package com.example.primerparcial_kotlin.interfaces

import com.example.primerparcial_kotlin.models.Joke

interface CategoryActivityInterface {

    fun onSuccess(response: ArrayList<String>)
    fun onError()
}
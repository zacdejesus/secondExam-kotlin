package com.example.primerparcial_kotlin.services

import com.example.primerparcial_kotlin.models.Joke
import retrofit2.http.GET
import retrofit2.http.Url
import retrofit2.Response

interface ApiService {
    @GET
    suspend fun getJoke(@Url url: String): Response<Joke>

    @GET
    suspend fun getCategories(@Url url: String): Response<ArrayList<String>>
}
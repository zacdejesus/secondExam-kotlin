package com.example.primerparcial_kotlin.models

data class Joke (
    val categories: List<Any?>,
    val createdAt: String,
    val iconURL: String,
    val id: String,
    val updatedAt: String,
    val url: String,
    val value: String)
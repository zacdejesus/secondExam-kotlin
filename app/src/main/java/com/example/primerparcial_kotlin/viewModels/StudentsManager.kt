package com.example.primerparcial_kotlin.viewModels

import android.content.Context
import com.example.primerparcial_kotlin.models.Joke
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StudentsManager {
    fun saveStudentListToSharedPreferences(context: Context, studentList: MutableList<Joke>) {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val gson = Gson()
        val studentListJson = gson.toJson(studentList)
        sharedPreferences.edit().putString("studentListKey", studentListJson).apply()
    }

    fun retrieveStudentListFromSharedPreferences(context: Context): MutableList<Joke> {
        val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val studentListJson = sharedPreferences.getString("studentListKey", null)
        val gson = Gson()
        if (studentListJson == null) {
            return emptyList<Joke>().toMutableList()
        }
        return gson.fromJson(studentListJson, object : TypeToken<MutableList<Joke>>() {}.type)
    }
}
package com.example.primerparcial_kotlin.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.primerparcial_kotlin.R
import com.example.primerparcial_kotlin.adapter.StudentAdapter
import com.example.primerparcial_kotlin.databinding.ActivityMainBinding
import com.example.primerparcial_kotlin.models.Joke
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.viewModels.StudentsManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.primerparcial_kotlin.services.Retrofit

class MainActivity : AppCompatActivity() {

    private val studentsManager = StudentsManager()
    private var students = mutableListOf<Joke>()

    private lateinit var adapter: StudentAdapter
    private  lateinit var binding: ActivityMainBinding

    // LifeCycle
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerStudent.layoutManager = LinearLayoutManager(this)
        showAddStudentsActivity()
        setCateogies()
    }

    private fun showAddStudentsActivity() {
        val intent = Intent(this, SecondActivity::class.java)
        startActivity(intent)
    }

    private fun setCateogies() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getCategories("jokes/categories")
            val response = call.body()

            runOnUiThread {
                // perform UI actions
                if (call.isSuccessful && response != null) {

                    adapter = StudentAdapter(response)
                    binding.recyclerStudent.adapter = adapter

                    adapter.notifyDataSetChanged()
                } else {
                    Toast.makeText(this@MainActivity, "Error en la llamada a la API", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    // Methods
}
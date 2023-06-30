package com.example.primerparcial_kotlin.viewModels

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.primerparcial_kotlin.adapter.CategoryAdapter
import com.example.primerparcial_kotlin.interfaces.CategoryActivityInterface
import com.example.primerparcial_kotlin.services.ApiService
import com.example.primerparcial_kotlin.services.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesViewModel: ViewModel()  {

    var listener: CategoryActivityInterface? = null

    fun getCategories() {
        CoroutineScope(Dispatchers.IO).launch {
            val call = Retrofit.getRetrofit().create(ApiService::class.java).getCategories("jokes/categories")
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
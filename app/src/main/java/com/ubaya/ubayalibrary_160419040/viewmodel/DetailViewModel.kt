package com.ubaya.ubayalibrary_160419040.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayalibrary_160419040.model.Book

class DetailViewModel(application: Application) : AndroidViewModel(application) {
    val bookLiveData = MutableLiveData<Book>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun fetch(idbook:String?){
        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419040/book.php?idbook=$idbook"

        val stringRequest= StringRequest(
            Request.Method.GET,
            url,
            {
                val sType =object : TypeToken<Book>(){}.type
                val result = Gson().fromJson<Book>(it, sType)
                bookLiveData.value = result
                Log.d("showvolley", it)
            },
            {
                Log.d("errorvolley",it.toString())
            }

        ).apply {
            tag = "TAG"
        }
        queue?.add(stringRequest)
    }

    override fun onCleared() {
        super.onCleared()
        queue?.cancelAll(TAG)
    }
}
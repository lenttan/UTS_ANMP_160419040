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

class BookViewModel(application: Application) : AndroidViewModel(application) {
    val bookLiveData = MutableLiveData<ArrayList<Book>>()
    val bookLoadError = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        bookLiveData.value = arrayListOf()

        bookLoadError.value =false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419040/book.php"

        val stringRequest= StringRequest(
            Request.Method.GET,
            url,
            {
                val sType =object :TypeToken<ArrayList<Book>>(){}.type
                val result =Gson().fromJson<ArrayList<Book>>(it, sType)
                bookLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                bookLoadError.value = true
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
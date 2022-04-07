package com.ubaya.ubayalibrary_160419040.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.ubayalibrary_160419040.model.Author

class AuthorViewModel(application: Application) : AndroidViewModel(application) {
    val authorLiveData = MutableLiveData<ArrayList<Author>>()
    val authorLoadError = MutableLiveData<Boolean>()
    val loadingLiveData = MutableLiveData<Boolean>()
    val TAG = "volleyTag"
    private var queue: RequestQueue? = null

    fun refresh(){
        authorLiveData.value = arrayListOf()

        authorLoadError.value =false
        loadingLiveData.value = true

        queue = Volley.newRequestQueue(getApplication())
        val url = "https://ubaya.fun/native/160419040/author.php"

        val stringRequest= StringRequest(
            Request.Method.GET,
            url,
            {
                val sType =object : TypeToken<ArrayList<Author>>(){}.type
                val result = Gson().fromJson<ArrayList<Author>>(it, sType)
                authorLiveData.value = result
                loadingLiveData.value = false
                Log.d("showvolley", it)
            },
            {
                loadingLiveData.value = false
                authorLoadError.value = true
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
package com.ubaya.ubayalibrary_160419040.model

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class Book(
    var id: String?,
    var title: String?,
    var description: String?,
    var author: String?,
    @SerializedName("photo_url")
    var photoUrl:String?,
    @SerializedName("release_year")
    var releaseYear: String?,
    var publisher: String?
)

data class Publisher(
    var id: String?,
    var name: String?,
    var address: String?,
    var website: String?)

data class Author(
    var id: String?,
    var name:String?,
    @SerializedName("photo_url")
    var photoUrl: String?
)
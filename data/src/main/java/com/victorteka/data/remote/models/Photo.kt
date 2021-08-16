package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("thumb")
    val thumb: String
)
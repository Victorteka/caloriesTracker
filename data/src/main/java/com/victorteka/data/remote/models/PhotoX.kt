package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class PhotoX(
    @SerializedName("highres")
    val highres: String,
    @SerializedName("is_user_uploaded")
    val isUserUploaded: Boolean,
    @SerializedName("thumb")
    val thumb: String
)
package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class FoodDetailsResponse(
    @SerializedName("foods")
    val foods: List<Food>,
    @SerializedName("photo")
    val photo: PhotoX
)
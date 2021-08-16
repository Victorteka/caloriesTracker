package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class SearchResponse(
    @SerializedName("common")
    val common: List<Common>
)
package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class Common(
    @SerializedName("common_type")
    val commonType: Any,
    @SerializedName("food_name")
    val foodName: String,
    @SerializedName("locale")
    val locale: String,
    @SerializedName("photo")
    val photo: Photo,
    @SerializedName("serving_qty")
    val servingQty: Int,
    @SerializedName("serving_unit")
    val servingUnit: String,
    @SerializedName("tag_id")
    val tagId: String,
    @SerializedName("tag_name")
    val tagName: String
)
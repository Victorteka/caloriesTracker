package com.victorteka.data.remote.models


import com.google.gson.annotations.SerializedName

data class Food(
    @SerializedName("brand_name")
    val brandName: Any,
    @SerializedName("food_name")
    val foodName: String,
    @SerializedName("nf_calories")
    val nfCalories: Double,
    @SerializedName("nf_cholesterol")
    val nfCholesterol: Double,
    @SerializedName("nf_dietary_fiber")
    val nfDietaryFiber: Int,
    @SerializedName("nf_p")
    val nfP: Double,
    @SerializedName("nf_potassium")
    val nfPotassium: Double,
    @SerializedName("nf_protein")
    val nfProtein: Double,
    @SerializedName("nf_saturated_fat")
    val nfSaturatedFat: Double,
    @SerializedName("nf_sodium")
    val nfSodium: Double,
    @SerializedName("nf_sugars")
    val nfSugars: Int,
    @SerializedName("nf_total_carbohydrate")
    val nfTotalCarbohydrate: Int,
    @SerializedName("nf_total_fat")
    val nfTotalFat: Double,
    @SerializedName("serving_qty")
    val servingQty: Int,
    @SerializedName("serving_unit")
    val servingUnit: String,
    @SerializedName("serving_weight_grams")
    val servingWeightGrams: Int
)
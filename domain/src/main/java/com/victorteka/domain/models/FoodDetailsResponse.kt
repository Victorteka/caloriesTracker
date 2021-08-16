package com.victorteka.domain.models

data class FoodDetailsResponse(
    val foods: List<Food>,
    val photo: PhotoX
)

data class Food(
    val brand_name: Any,
    val food_name: String,
    val nf_calories: Double,
    val nf_cholesterol: Double,
    val nf_dietary_fiber: Int,
    val nf_p: Double,
    val nf_potassium: Double,
    val nf_protein: Double,
    val nf_saturated_fat: Double,
    val nf_sodium: Double,
    val nf_sugars: Int,
    val nf_total_carbohydrate: Int,
    val nf_total_fat: Double,
    val serving_qty: Int,
    val serving_unit: String,
    val serving_weight_grams: Int
)

data class PhotoX(
    val highres: String,
    val is_user_uploaded: Boolean,
    val thumb: String
)
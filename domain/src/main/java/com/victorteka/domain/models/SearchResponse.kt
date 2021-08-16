package com.victorteka.domain.models

data class SearchResponse(
    val common: List<Common>
)

data class Common(
    val food_name: String,
    val locale: String,
    val photo: Photo,
    val serving_qty: Int,
    val serving_unit: String,
    val tag_id: String,
    val tag_name: String,
    )

data class Photo(
    val thumb: String
)

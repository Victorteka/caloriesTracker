package com.victorteka.domain.services

import com.victorteka.domain.Result
import com.victorteka.domain.models.FoodDetailsResponse
import com.victorteka.domain.models.SearchResponse

interface FoodStatsService{

    suspend fun searchFoodStats(queryTerm: String): Result<SearchResponse>

    suspend fun queryFoodDetails(queryTerm: HashMap<String, String>): Result<FoodDetailsResponse>
}
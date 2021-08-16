package com.victorteka.domain.repository

import com.victorteka.domain.Result
import com.victorteka.domain.models.Common
import com.victorteka.domain.models.FoodDetailsResponse
import kotlinx.coroutines.flow.Flow

interface FoodStatsRepository {

    fun searchFoodStats(queryTerm: String): Flow<Result<List<Common>>>

    fun queryFoodDetails(queryTerm: HashMap<String, String>): Flow<Result<FoodDetailsResponse>>

    fun fetchFood(): Flow<Result<List<Common>>>

    suspend fun deleteFoodStats()
}
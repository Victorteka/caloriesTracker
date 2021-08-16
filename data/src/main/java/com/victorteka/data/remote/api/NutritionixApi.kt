package com.victorteka.data.remote.api

import com.victorteka.domain.models.FoodDetailsResponse
import com.victorteka.domain.models.SearchResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface NutritionixApi {

    @GET("search/instant")
    suspend fun searchFood(
        @Query("query") queryString: String
    ): Response<SearchResponse>

    @POST("natural/nutrients")
    suspend fun queryFoodDetails(
        @Body queryPayload: HashMap<String, String>
    ): Response<FoodDetailsResponse>
}
package com.victorteka.caloriestracker.ui.fooddetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.victorteka.domain.Result
import com.victorteka.domain.models.FoodDetailsResponse
import com.victorteka.domain.repository.FoodStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FoodDetailsViewModel @Inject constructor(
    private val foodStatsRepository: FoodStatsRepository
) : ViewModel(){

    fun foodDetail(queryTerm: HashMap<String, String>): LiveData<Result<FoodDetailsResponse>> {
        return foodStatsRepository.queryFoodDetails(queryTerm).asLiveData()
    }

}
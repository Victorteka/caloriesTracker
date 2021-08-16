package com.victorteka.caloriestracker.ui.newMeal.recently

import androidx.lifecycle.*
import com.victorteka.domain.Result
import com.victorteka.domain.models.Common
import com.victorteka.domain.repository.FoodStatsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecentlyViewModel @Inject constructor(
    private val foodStatsRepository: FoodStatsRepository,
) : ViewModel() {

    val foodFound = foodStatsRepository.fetchFood().asLiveData()

    fun searchFood(queryTerm: String): LiveData<Result<List<Common>>> {
        viewModelScope.launch {
            foodStatsRepository.deleteFoodStats()
        }
        return foodStatsRepository.searchFoodStats(queryTerm).asLiveData()
    }

}
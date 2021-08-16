package com.victorteka.data.local.repo

import com.victorteka.data.local.db.FoodStatsDao
import com.victorteka.data.local.toDomain
import com.victorteka.data.local.toEntity
import com.victorteka.domain.Result
import com.victorteka.domain.models.Common
import com.victorteka.domain.models.FoodDetailsResponse
import com.victorteka.domain.repository.FoodStatsRepository
import com.victorteka.domain.services.FoodStatsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

internal class FoodStatsRepoImpl @Inject constructor(
    private val foodStatsDao: FoodStatsDao,
    private val foodStatsService: FoodStatsService
) : FoodStatsRepository {
    override fun searchFoodStats(queryTerm: String): Flow<Result<List<Common>>> = flow {
        emit(Result.Loading)
        if (queryTerm != null || queryTerm != "") {
            when (val result = foodStatsService.searchFoodStats(queryTerm)) {
                is Result.Error -> emit(Result.error(result.exception))
                is Result.Success -> {
                    val entities = result.data.common.map { it.toEntity() }
                    foodStatsDao.save(entities)
                    foodStatsDao.fetchAllFood().collect { list ->
                        emit(Result.Success(list.map { it.toDomain() }))
                    }
                }
            }
        } else {
            foodStatsDao.fetchAllFood().collect { foodList ->
                emit(Result.Success(foodList.map { it.toDomain() }))
            }
        }
    }

    override fun queryFoodDetails(queryTerm: HashMap<String, String>): Flow<Result<FoodDetailsResponse>>  = flow {
        emit(Result.Loading)
        when(val result = foodStatsService.queryFoodDetails(queryTerm)){
            is Result.Error -> emit(Result.error(result.exception))
            is Result.Success -> {
                emit(Result.Success(result.data))
            }
        }
    }

    override fun fetchFood(): Flow<Result<List<Common>>> = flow {
        emit(Result.Loading)
        foodStatsDao.fetchAllFood().collect { foodList ->
            emit(Result.Success(foodList.map { it.toDomain() }))
        }
    }

    override suspend fun deleteFoodStats() {
        foodStatsDao.clearFoodStatsTable()
    }
}
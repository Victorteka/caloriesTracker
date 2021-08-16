package com.victorteka.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
internal interface FoodStatsDao {

    @Insert(onConflict = REPLACE)
    suspend fun save(entries: List<FoodStatsEntity>)

    @Query("select * from food_stats")
    fun fetchAllFood(): Flow<List<FoodStatsEntity>>

    @Query("select count(*) from food_stats")
    suspend fun count(): Int

    @Query("delete from food_stats")
    suspend fun clearFoodStatsTable()
}
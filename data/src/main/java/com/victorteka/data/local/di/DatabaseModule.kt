package com.victorteka.data.local.di

import android.content.Context
import com.victorteka.data.local.db.FoodStatsDao
import com.victorteka.data.local.db.NutritionixDB
import com.victorteka.data.local.repo.FoodStatsRepoImpl
import com.victorteka.domain.repository.FoodStatsRepository
import com.victorteka.domain.services.FoodStatsService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    internal fun provideFoodDatabase(context: Context): NutritionixDB =
        NutritionixDB.getInstance(context)

    @Provides
    @Singleton
    internal fun provideFoodStatsDao(database: NutritionixDB): FoodStatsDao =
        database.foodStatsDao()

    @Provides
    @Singleton
    internal fun provideFoodStatsRepository(
        foodStatsDao: FoodStatsDao,
        foodStatsService: FoodStatsService
    ): FoodStatsRepository = FoodStatsRepoImpl(foodStatsDao, foodStatsService)
}
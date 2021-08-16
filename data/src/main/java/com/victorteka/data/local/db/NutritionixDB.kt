package com.victorteka.data.local.db

import android.content.Context
import androidx.room.*

@Database(entities = [FoodStatsEntity::class], version = 1, exportSchema = false)
internal abstract class NutritionixDB : RoomDatabase() {

    abstract fun foodStatsDao(): FoodStatsDao

    companion object {
        var database: NutritionixDB? = null

        fun getInstance(context: Context): NutritionixDB {
            database?.let {
                return it
            }
            return Room.databaseBuilder(context, NutritionixDB::class.java, "food_db")
                .build()
        }
    }

}
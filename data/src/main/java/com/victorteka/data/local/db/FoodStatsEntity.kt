package com.victorteka.data.local.db

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.victorteka.domain.models.Photo


@Entity(tableName = "food_stats")
internal data class FoodStatsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long?,

    @ColumnInfo(name = "food_name")
    val foodName: String,

    val locale: String,

    @Embedded
    val photo: Photo,

    @ColumnInfo(name = "serving_qty")
    val servingQty: Int,

    @ColumnInfo(name = "serving_unit")
    val servingUnit: String,

    @ColumnInfo(name = "tag_id")
    val tagId: String,

    @ColumnInfo(name = "tag_name")
    val tagName: String

)

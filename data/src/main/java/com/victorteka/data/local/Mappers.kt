package com.victorteka.data.local

import com.victorteka.data.local.db.FoodStatsEntity
import com.victorteka.domain.models.Common

internal fun Common.toEntity() = FoodStatsEntity(
    null,
    this.food_name,
    this.locale,
    this.photo,
    this.serving_qty,
    this.serving_unit,
    this.tag_id,
    this.tag_name
)

internal fun FoodStatsEntity.toDomain() = Common(
    this.foodName,
    this.locale,
    this.photo,
    this.servingQty,
    this.servingUnit,
    this.tagId,
    this.tagName
)
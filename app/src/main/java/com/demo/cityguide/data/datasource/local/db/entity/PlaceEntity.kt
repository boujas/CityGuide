package com.demo.cityguide.data.datasource.local.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey val id: String,
    val name: String,
    val instagram: String,
    val address: String,
    val type: String,
    val isActive: Boolean
)
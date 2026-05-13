package com.demo.cityguide.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.demo.cityguide.data.datasource.local.db.entity.PlaceEntity

@Dao
interface PlacesDao {

    @Query("SELECT * FROM places")
    suspend fun getAll(): List<PlaceEntity>

    @Upsert
    suspend fun upsertAll(places: List<PlaceEntity>)

}
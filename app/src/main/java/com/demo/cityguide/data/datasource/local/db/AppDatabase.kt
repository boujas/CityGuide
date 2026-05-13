package com.demo.cityguide.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.demo.cityguide.data.datasource.local.db.dao.PlacesDao
import com.demo.cityguide.data.datasource.local.db.entity.PlaceEntity

@Database(entities = [PlaceEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placesDao(): PlacesDao
}
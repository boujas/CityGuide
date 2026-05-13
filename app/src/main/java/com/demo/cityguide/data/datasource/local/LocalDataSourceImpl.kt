package com.demo.cityguide.data.datasource.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.demo.cityguide.data.datasource.local.db.dao.PlacesDao
import com.demo.cityguide.data.datasource.local.db.mapper.PlaceEntityMapper
import com.demo.cityguide.data.model.PlaceDto
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val dao: PlacesDao,
    private val mapper: PlaceEntityMapper,
    private val dataStore: DataStore<Preferences>
) : LocalDataSource {

    override suspend fun getPlaces(): List<PlaceDto> =
        dao.getAll().map(mapper::toDto)

    override suspend fun upsertPlaces(places: List<PlaceDto>) =
        dao.upsertAll(places.map(mapper::toEntity))

    override suspend fun getLastUpdated(): Long? =
        dataStore.data.first()[LAST_UPDATED_KEY]

    override suspend fun saveLastUpdated(timestamp: Long) {
        dataStore.edit { it[LAST_UPDATED_KEY] = timestamp }
    }

    companion object {
        val LAST_UPDATED_KEY = longPreferencesKey("places_last_updated")
    }

}
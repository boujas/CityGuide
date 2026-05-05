package com.demo.cityguide.data.datasource.remote

import com.demo.cityguide.data.model.PlaceDto
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(private val firestore: FirebaseFirestore) :
    RemoteDataSource {
    override suspend fun getAllPlaces(): List<PlaceDto> {
        return firestore.collection("places")
            .get()
            .await()
            .toObjects(PlaceDto::class.java)
    }

    override suspend fun addPlace(place: PlaceDto) {
        TODO("Not yet implemented")
    }
}
package com.demo.cityguide.domain.usecase

import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val repository: PlacesRepository) {
    suspend operator fun invoke(): Result<List<Place>> =
        runCatching { repository.getPlaces() }
}
package com.demo.cityguide.domain.usecase

import com.demo.cityguide.domain.model.PlacesResult
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class GetPlacesUseCase @Inject constructor(private val repository: PlacesRepository) {
    suspend operator fun invoke(): Result<PlacesResult> =
        runCatching { repository.getPlaces() }
}
package com.demo.cityguide.domain.usecase

import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.model.PlaceType
import com.demo.cityguide.domain.repository.PlacesRepository
import javax.inject.Inject

class GetPlaceDetailsUseCase @Inject constructor(private val repository: PlacesRepository) {

    suspend operator fun invoke(placeId: String): Result<Place> {
        // TODO replace with repository call
        return Result.success(
            Place(
                id = placeId,
                name = "Fake place",
                instagram = "fake_place",
                address = "Dnipro, fake address",
                type = PlaceType.BAR,
                isActive = true
            )
        )
    }
}
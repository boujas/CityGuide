package com.demo.cityguide.data.mapper

import com.demo.cityguide.data.model.PlaceDto
import com.demo.cityguide.domain.model.Place
import com.demo.cityguide.domain.model.PlaceType
import javax.inject.Inject

class PlaceMapper @Inject constructor() {

    fun toDomain(dto: PlaceDto): Place = Place(
        id = dto.id,
        name = dto.name,
        instagram = dto.instagram,
        address = dto.address,
        type = mapType(dto.type),
        isActive = dto.isActive
    )

    fun toDto(domain: Place): PlaceDto = PlaceDto(
        id = domain.id,
        name = domain.name,
        instagram = domain.instagram,
        address = domain.address,
        type = domain.type.name,
        isActive = domain.isActive
    )

    private fun mapType(value: String): PlaceType = runCatching {
        PlaceType.valueOf(value)
    }.getOrDefault(PlaceType.CAFE)
}
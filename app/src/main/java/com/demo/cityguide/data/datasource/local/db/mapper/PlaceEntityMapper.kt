package com.demo.cityguide.data.datasource.local.db.mapper

import com.demo.cityguide.data.datasource.local.db.entity.PlaceEntity
import com.demo.cityguide.data.model.PlaceDto
import javax.inject.Inject

class PlaceEntityMapper @Inject constructor() {

    fun toEntity(dto: PlaceDto): PlaceEntity = PlaceEntity(
        id = dto.id,
        name = dto.name,
        instagram = dto.instagram,
        address = dto.address,
        type = dto.type,
        isActive = dto.isActive
    )

    fun toDto(entity: PlaceEntity): PlaceDto = PlaceDto(
        id = entity.id,
        name = entity.name,
        instagram = entity.instagram,
        address = entity.address,
        type = entity.type,
        isActive = entity.isActive
    )
}
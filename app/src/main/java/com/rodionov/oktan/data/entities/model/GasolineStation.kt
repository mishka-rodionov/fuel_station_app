package com.rodionov.oktan.data.entities.model

class GasolineStation(
        id: String,
        type: FuelStationType = FuelStationType.GASOLINE,
        services: List<FuelStationServices>?,
        coordinates: Coordinates?,
        brand: String?,
        dateOfCreation: Long?,
        creatorId: String?,
        val gasolineTypes: List<GasolineType>? = null
) : FuelStation(type = type, services = services, coordinates = coordinates, brand = brand, id = id, dateOfCreation = dateOfCreation, creatorId = creatorId)
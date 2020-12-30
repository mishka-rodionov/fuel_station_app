package com.rodionov.oktan.data.entities.model

class GasolineStation(
        val gs_id: String? = null,
        type: FuelStationType = FuelStationType.GASOLINE,
        services: List<FuelStationServices>?,
        coordinates: Coordinates?,
        brand: String?,
        val gasolineTypes: List<GasolineType>? = null
): FuelStation(type = type, services = services, coordinates = coordinates, brand = brand)
package com.rodionov.oktan.data.entities.model

open class FuelStation(
        var type: FuelStationType? = null,
        var services: List<FuelStationServices>? = null,
        var coordinates: Coordinates? = null,
        var brand: String? = null
)

package com.rodionov.oktan.data.entities.model

abstract class FuelStation(
        var type: FuelStationType? = null,
        var services: FuelStationServices? = null,
        var coordinates: Coordinates? = null
)

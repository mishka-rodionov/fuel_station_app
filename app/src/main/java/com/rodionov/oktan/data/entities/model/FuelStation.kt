package com.rodionov.oktan.data.entities.model

open class FuelStation(
        var id: String,
        var type: FuelStationType? = null,
        var services: List<FuelStationServices>? = null,
        var coordinates: Coordinates? = null,
        var brand: String? = null,
        var dateOfCreation: Long? = null,
        var creatorId: String? = null,
        var activeStatus: Boolean? = null
)

package com.rodionov.oktan.data.entities.model.gasoline

import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.FuelStationType

class GasolineStation(
        id: String = "syntheticId",
        type: FuelStationType = FuelStationType.GASOLINE,
        services: List<FuelStationServices>? = emptyList(),
        coordinates: Coordinates? = null,
        brand: String? = null,
        dateOfCreation: Long? = null,
        creatorId: String? = null,
        activeStatus: Boolean? = null,
        var gasolineTypes: List<GasolineType>? = emptyList()
) : FuelStation(type = type, services = services, coordinates = coordinates, brand = brand, id = id, dateOfCreation = dateOfCreation, creatorId = creatorId, activeStatus = activeStatus)
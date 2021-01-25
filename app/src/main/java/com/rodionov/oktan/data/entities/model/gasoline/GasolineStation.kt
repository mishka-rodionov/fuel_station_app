package com.rodionov.oktan.data.entities.model.gasoline

import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.FuelStationType

class GasolineStation(
        id: String,
        type: FuelStationType = FuelStationType.GASOLINE,
        services: List<FuelStationServices>?,
        coordinates: Coordinates?,
        brand: String?,
        dateOfCreation: Long?,
        creatorId: String?,
        activeStatus: Boolean?,
        val gasolineTypes: List<GasolineType>? = null
) : FuelStation(type = type, services = services, coordinates = coordinates, brand = brand, id = id, dateOfCreation = dateOfCreation, creatorId = creatorId, activeStatus = activeStatus)
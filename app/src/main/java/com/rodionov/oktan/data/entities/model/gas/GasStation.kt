package com.rodionov.oktan.data.entities.model.gas

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.FuelStationType

class GasStation(
        type: FuelStationType = FuelStationType.GAS,
        services: List<FuelStationServices>?,
        coordinates: Coordinates?,
        brand: String?,
        id: String,
        dateOfCreation: Long?,
        creatorId: String?,
        activeStatus: Boolean?,
        @SerializedName("gas_types")
        val gasTypes: List<GasType>? = null
) : FuelStation(
        type = type,
        services = services,
        coordinates = coordinates,
        brand = brand,
        id = id,
        dateOfCreation = dateOfCreation,
        creatorId = creatorId,
        activeStatus = activeStatus
)
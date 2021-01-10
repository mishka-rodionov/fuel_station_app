package com.rodionov.oktan.data.entities.model.electric

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.FuelStationType

class ChargingStation(
        type: FuelStationType = FuelStationType.ELECTRIC,
        services: List<FuelStationServices>?,
        coordinates: Coordinates?,
        brand: String?,
        id: String,
        dateOfCreation: Long?,
        creatorId: String?,
        @SerializedName("charging_types")
        val chargeTypes: List<ChargeType>? = null,
        @SerializedName("connector_types")
        val connectorTypes: List<ConnectorType>? = null
) : FuelStation(
        type = type,
        services = services,
        coordinates = coordinates,
        brand = brand,
        id = id,
        dateOfCreation = dateOfCreation,
        creatorId = creatorId
)
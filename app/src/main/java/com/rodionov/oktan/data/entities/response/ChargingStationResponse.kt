package com.rodionov.oktan.data.entities.response

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.electric.ChargeType
import com.rodionov.oktan.data.entities.model.electric.ConnectorType
import com.rodionov.oktan.data.entities.model.gasoline.GasolineType

data class ChargingStationResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("services")
        val services: List<FuelStationServices>? = null,
        @SerializedName("coordinates")
        val coordinates: Coordinates? = null,
        @SerializedName("brand")
        val brand: String? = null,
        @SerializedName("charging_types")
        val chargeTypes: List<ChargeType>? = null,
        @SerializedName("connector_types")
        val connectorTypes: List<ConnectorType>? = null,
        @SerializedName("date_of_creation")
        val dateOfCreation: Long? = null,
        @SerializedName("creator_id")
        val creatorId: String? = null

)

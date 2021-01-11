package com.rodionov.oktan.data.entities.response

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.gas.GasType

data class GasStationResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("services")
        val services: List<FuelStationServices>? = null,
        @SerializedName("coordinates")
        val coordinates: Coordinates? = null,
        @SerializedName("brand")
        val brand: String? = null,
        @SerializedName("gas_types")
        val gasTypes: List<GasType>? = null,
        @SerializedName("date_of_creation")
        val dateOfCreation: Long? = null,
        @SerializedName("creator_id")
        val creatorId: String? = null

)

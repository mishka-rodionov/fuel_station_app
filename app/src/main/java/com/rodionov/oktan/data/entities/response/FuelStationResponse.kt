package com.rodionov.oktan.data.entities.response

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.FuelStationType

data class FuelStationResponse(
        @SerializedName("type")
        var type: FuelStationType? = null,
        @SerializedName("services")
        var services: List<FuelStationServices>? = null,
        @SerializedName("coordinates")
        var coordinates: Coordinates? = null,
        @SerializedName("brand")
        var brand: String? = null
)
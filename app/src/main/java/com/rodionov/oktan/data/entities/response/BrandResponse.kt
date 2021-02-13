package com.rodionov.oktan.data.entities.response

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.FuelStationType

data class BrandResponse(
        @SerializedName("name")
        val name: String? = null,
        @SerializedName("fuel_station_type")
        val fuelStationType: FuelStationType? = null
)

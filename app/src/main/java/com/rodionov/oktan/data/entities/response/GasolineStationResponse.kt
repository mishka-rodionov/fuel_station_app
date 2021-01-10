package com.rodionov.oktan.data.entities.response

import com.google.gson.annotations.SerializedName
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.gasoline.GasolineType

data class GasolineStationResponse(
        @SerializedName("id")
        val id: String,
        @SerializedName("services")
        val services: List<FuelStationServices>? = null,
        @SerializedName("coordinates")
        val coordinates: Coordinates? = null,
        @SerializedName("brand")
        val brand: String? = null,
        @SerializedName("gasoline_types")
        val gasolineTypes: List<GasolineType>? = null,
        @SerializedName("date_of_creation")
        val dateOfCreation: Long? = null,
        @SerializedName("creator_id")
        val creatorId: String? = null

)

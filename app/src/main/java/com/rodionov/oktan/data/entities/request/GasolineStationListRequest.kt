package com.rodionov.oktan.data.entities.request

import com.google.gson.annotations.SerializedName

data class GasolineStationListRequest(
        @SerializedName("brand")
        val brand: String? = null,
        @SerializedName("offset")
        val offset: Int? = null,
        @SerializedName("limit")
        val limit: Int? = null
)
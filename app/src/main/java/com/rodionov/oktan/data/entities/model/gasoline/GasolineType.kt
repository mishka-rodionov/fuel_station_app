package com.rodionov.oktan.data.entities.model.gasoline

import com.google.gson.annotations.SerializedName

data class GasolineType(
        @SerializedName("name")
        val name: GasolineName? = null,
        @SerializedName("price_per_liter")
        val pricePerLiter: Float? = null,
        @SerializedName("real_oktan_number")
        val realOktanNumber: Float? = null
)
package com.rodionov.oktan.data.entities.model.gasoline

data class GasolineType(
        val name: GasolineName? = null,
        val pricePerLiter: Float? = null,
        val realOktanNumber: Float? = null
)
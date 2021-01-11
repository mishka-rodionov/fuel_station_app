package com.rodionov.oktan.domain

import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation

interface FuelStationRepository {

    fun getGasolineStations(onSuccess: (List<GasolineStation>) -> Unit, onError: (Throwable) -> Unit)

}
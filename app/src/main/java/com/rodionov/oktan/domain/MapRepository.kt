package com.rodionov.oktan.domain

import com.rodionov.oktan.data.entities.model.Brand
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation

interface MapRepository {

    fun createLocalGasolineStation(gasolineStation: GasolineStation)
    fun getAllGasolineStation(onSuccess: (List<GasolineStation>) -> Unit, onError: (Throwable) -> Unit)
//    fun getFuelStations(onSuccess: (FuelStation) -> Unit, onError: (Throwable) -> Unit)
    fun createRemoteGasolineStation(gasolineStation: GasolineStation, onSuccess: (GasolineStation) -> Unit, onError: (Throwable) -> Unit)
    fun getAllFuelStationBrands(onSuccess: (List<Brand>) -> Unit, onError: (Throwable) -> Unit)

}
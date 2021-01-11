package com.rodionov.oktan.presentation.fuel_station_list

import android.util.Log
import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.domain.FuelStationRepository

class FuelStationListViewModel(
        private val fuelStationRepository: FuelStationRepository
) : BaseViewModel() {

    fun getGasolineStations() {
        fuelStationRepository.getGasolineStations(onSuccess = ::handleGasolineStations, onError = ::onError)
    }

    private fun handleGasolineStations(gasolineStations: List<GasolineStation>) {
        gasolineStations.forEach {
            Log.d(TAG, "handleGasolineStations: brand = ${it.brand}")
        }
    }

}
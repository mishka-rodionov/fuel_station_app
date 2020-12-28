package com.rodionov.oktan.presentation.map

import android.location.Location
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.app.utils.LocationProvider
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.domain.MapRepository

class MapViewModel(
        private val mapRepository: MapRepository,
        private val locationProvider: LocationProvider
) : BaseViewModel() {

    val stations = MutableLiveData<List<GasolineStation>>()
    val currentLocation = MutableLiveData<Location>()

    init {
        mapRepository.getFuelStations(onSuccess = ::handleFuelStations, onError = ::handleError)
    }

    fun createGasolineStation(gasolineStation: GasolineStation) {
        mapRepository.createGasolineStation(gasolineStation = gasolineStation)
    }

    fun getAllGasolineStation() {
        Log.d(TAG, "getAllGasolineStation: ")
        mapRepository.getAllGasolineStation(onSuccess = ::handleShowGasolineStation, onError = ::handleError)
    }

    private fun handleShowGasolineStation(stations: List<GasolineStation>) {
        Log.d(TAG, "handleShowGasolineStation: ")
        this.stations.value = stations
    }

    private fun handleError(throwable: Throwable) {
        Log.d(TAG, "handleError: cause = ${throwable.cause}")
    }

    fun getCurrentLocation() {
        locationProvider.getLocation({
            currentLocation.postValue(it)
        }, {

        })
    }

    fun handleFuelStations(fuelStation: FuelStation) {

    }

}
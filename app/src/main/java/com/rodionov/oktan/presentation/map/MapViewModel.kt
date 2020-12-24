package com.rodionov.oktan.presentation.map

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.domain.MapRepository

class MapViewModel(
        private val mapRepository: MapRepository
) : BaseViewModel() {

    val stations = MutableLiveData<List<GasolineStation>>()

    fun createGasolineStation(gasolineStation: GasolineStation) {
        mapRepository.createGasolineStation(gasolineStation = gasolineStation)
    }

    fun getAllGasolineStation() {
        mapRepository.getAllGasolineStation(onSuccess = ::handleShowGasolineStation, onError = ::handleError)
    }

    private fun handleShowGasolineStation(stations: List<GasolineStation>) {
        this.stations.value = stations
    }

    private fun handleError(throwable: Throwable) {
        Log.d(TAG, "handleError: cause = ${throwable.cause}")
    }

}
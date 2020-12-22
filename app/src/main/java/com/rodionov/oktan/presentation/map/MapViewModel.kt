package com.rodionov.oktan.presentation.map

import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.domain.MapRepository

class MapViewModel(
        private val mapRepository: MapRepository
) : BaseViewModel() {

    fun createGasolineStation(gasolineStation: GasolineStation) {
        mapRepository.createGasolineStation(gasolineStation = gasolineStation)
    }

}
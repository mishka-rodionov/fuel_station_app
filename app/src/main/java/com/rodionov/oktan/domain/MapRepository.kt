package com.rodionov.oktan.domain

import com.rodionov.oktan.data.entities.model.GasolineStation

interface MapRepository {

    fun createGasolineStation(gasolineStation: GasolineStation)

}
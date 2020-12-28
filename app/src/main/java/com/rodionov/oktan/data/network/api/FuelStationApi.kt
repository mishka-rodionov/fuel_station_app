package com.rodionov.oktan.data.network.api

import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.response.FuelStationResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface FuelStationApi {

    @GET(GET_FUEL_STATIONS)
    fun getFuelStations(): Observable<FuelStationResponse>

    companion object {
        const val GET_FUEL_STATIONS = "/fuel_stations"
    }

}
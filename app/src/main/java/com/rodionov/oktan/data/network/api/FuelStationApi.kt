package com.rodionov.oktan.data.network.api

import com.rodionov.oktan.data.entities.request.GasolineStationRequest
import com.rodionov.oktan.data.entities.response.FuelStationResponse
import com.rodionov.oktan.data.entities.response.GasolineStationResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FuelStationApi {

    @GET(GET_FUEL_STATIONS)
    fun getFuelStations(): Observable<FuelStationResponse>

    @POST(NEW_GASOLINE_STATION)
    fun setGasolineStation(@Body gasolineStationRequest: GasolineStationRequest) : Observable<GasolineStationResponse>

    companion object {
        const val GET_FUEL_STATIONS = "/fuel_stations"
        const val NEW_GASOLINE_STATION = "/gasoline_station/new"
    }

}
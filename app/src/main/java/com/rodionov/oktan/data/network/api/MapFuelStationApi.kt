package com.rodionov.oktan.data.network.api

import com.rodionov.oktan.data.entities.request.GasolineStationRequest
import com.rodionov.oktan.data.entities.response.ChargingStationResponse
import com.rodionov.oktan.data.entities.response.GasStationResponse
import com.rodionov.oktan.data.entities.response.GasolineStationResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface MapFuelStationApi {

    @POST(NEW_GASOLINE_STATION)
    fun setGasolineStation(@Body gasolineStationRequest: GasolineStationRequest) : Observable<GasolineStationResponse>

    @GET(GET_GAS_STATIONS)
    fun getGasStations(): Observable<List<GasStationResponse>>

    @GET(GET_CHARGING_STATIONS)
    fun getChargingStations(): Observable<List<ChargingStationResponse>>

    @GET(GET_ALL_BRANDS)
    fun getAllFuelStationsBrands()

    companion object {
        const val NEW_GASOLINE_STATION = "/gasoline_station/new"
        const val GET_GAS_STATIONS = "/gas_stations"
        const val GET_CHARGING_STATIONS = "/charging_stations"
        const val GET_ALL_BRANDS = "/fuel_station/brands"
    }

}
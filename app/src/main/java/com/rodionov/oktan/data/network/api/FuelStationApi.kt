package com.rodionov.oktan.data.network.api

import com.rodionov.oktan.data.entities.request.GasolineStationListRequest
import com.rodionov.oktan.data.entities.request.GasolineStationRequest
import com.rodionov.oktan.data.entities.response.ChargingStationResponse
import com.rodionov.oktan.data.entities.response.FuelStationResponse
import com.rodionov.oktan.data.entities.response.GasStationResponse
import com.rodionov.oktan.data.entities.response.GasolineStationResponse
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface FuelStationApi {

    @GET(GET_FUEL_STATIONS)
    fun getFuelStations(): Observable<FuelStationResponse>

    @GET(GET_GASOLINE_STATIONS)
    fun getGasolineStations(): Observable<List<GasolineStationResponse>>

    @POST(GET_GASOLINE_STATION_LIST)
    fun getGasolineStationList(@Body gasolineStationListRequest: GasolineStationListRequest): Single<List<GasolineStationResponse>>

    @GET(GET_GAS_STATIONS)
    fun getGasStations(): Observable<List<GasStationResponse>>

    @GET(GET_CHARGING_STATIONS)
    fun getChargingStations(): Observable<List<ChargingStationResponse>>

    @POST(NEW_GASOLINE_STATION)
    fun setGasolineStation(@Body gasolineStationRequest: GasolineStationRequest) : Observable<GasolineStationResponse>

    @GET(GET_ALL_BRANDS)
    fun getAllFuelStationsBrands()

    companion object {
        const val GET_FUEL_STATIONS = "/fuel_stations"
        const val GET_GASOLINE_STATIONS = "/gasoline_stations"
        const val GET_GASOLINE_STATION_LIST = "/gasoline_station_list"
        const val GET_GAS_STATIONS = "/gas_stations"
        const val GET_CHARGING_STATIONS = "/charging_stations"
        const val NEW_GASOLINE_STATION = "/gasoline_station/new"
        const val GET_ALL_BRANDS = "/fuel_station/brands"
    }

}
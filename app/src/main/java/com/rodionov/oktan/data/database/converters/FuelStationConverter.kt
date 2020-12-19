package com.rodionov.oktan.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rodionov.oktan.app.extension.fromJson
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.GasolineType

class FuelStationConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGasolineStationDto(stations: List<GasolineStationDto>?): String? =
            gson.toJson(stations.orEmpty())

    @TypeConverter
    fun toGasolineStationDto(stations: String?) = gson.fromJson<List<GasolineStationDto>?>(stations.orEmpty())

    @TypeConverter
    fun fromGasolineType(types: List<GasolineType>?): String? =
            gson.toJson(types.orEmpty())

    @TypeConverter
    fun toGasolineType(types: String?) = gson.fromJson<List<GasolineType>?>(types.orEmpty())

    @TypeConverter
    fun fromGFuelStationServices(services: List<FuelStationServices>?): String? =
            gson.toJson(services.orEmpty())

    @TypeConverter
    fun toFuelStationServices(services: String?) = gson.fromJson<List<FuelStationServices>?>(services.orEmpty())


}
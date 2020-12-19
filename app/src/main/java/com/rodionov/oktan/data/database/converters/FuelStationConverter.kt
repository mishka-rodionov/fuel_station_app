package com.rodionov.oktan.data.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.rodionov.oktan.app.extension.fromJson
import com.rodionov.oktan.data.database.dto.GasolineStationDto

class FuelStationConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromGasolineStationDto(stations: List<GasolineStationDto>?): String? =
            gson.toJson(stations.orEmpty())

    @TypeConverter
    fun toGasolineStationDto(stations: String?) = gson.fromJson<List<GasolineStationDto>?>(stations.orEmpty())
}
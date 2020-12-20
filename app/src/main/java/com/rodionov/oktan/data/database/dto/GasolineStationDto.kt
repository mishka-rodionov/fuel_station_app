package com.rodionov.oktan.data.database.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.oktan.data.database.converters.FuelStationConverter
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.GasolineType

@Entity(tableName = "gasoline_station")
@TypeConverters(FuelStationConverter::class)
data class GasolineStationDto(
        @PrimaryKey
        val id: Int = 0,
        val services: List<FuelStationServices>? = null,
        val brand: String? = null,
        val gasolineTypes: List<GasolineType>? = null
)
package com.rodionov.oktan.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.oktan.data.database.converters.FuelStationConverter
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.GasolineType

@Entity(tableName = "gasoline_station")
@TypeConverters(FuelStationConverter::class)
data class GasolineStationDto(
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        val id: Int = 0,
        @ColumnInfo(name = "gs_id")
        val gs_id: String? = null,
        @ColumnInfo(name = "services")
        val services: List<FuelStationServices>? = null,
        @ColumnInfo(name = "brand")
        val brand: String? = null,
        @ColumnInfo(name = "gasoline_types")
        val gasolineTypes: List<GasolineType>? = null,
        @ColumnInfo(name = "coordinates")
        val coordinates: Coordinates? = null
)

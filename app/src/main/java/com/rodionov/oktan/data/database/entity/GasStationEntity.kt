package com.rodionov.oktan.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.oktan.data.database.converters.FuelStationConverter
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.gas.GasType

@Entity(tableName = "gas_stations")
@TypeConverters(FuelStationConverter::class)
data class GasStationEntity (
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "services")
        val services: List<FuelStationServices>? = null,
        @ColumnInfo(name = "brand")
        val brand: String? = null,
        @ColumnInfo(name = "gas_types")
        val gasTypes: List<GasType>? = null,
        @ColumnInfo(name = "coordinates")
        val coordinates: Coordinates? = null,
        @ColumnInfo(name = "date_of_creation")
        val dateOfCreation: String? = null,
        @ColumnInfo(name = "creator_id")
        val creatorId: String? = null
)
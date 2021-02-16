package com.rodionov.oktan.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.oktan.data.database.converters.FuelStationConverter
import com.rodionov.oktan.data.entities.model.FuelStationType

@Entity(tableName = "fuel_stations_brands")
@TypeConverters(FuelStationConverter::class)
data class BrandEntity(
        @PrimaryKey
        @ColumnInfo(name = "name")
        val name: String,
        @ColumnInfo(name = "fuelStationType")
        val fuelStationType: FuelStationType? = null
)

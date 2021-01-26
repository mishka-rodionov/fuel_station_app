package com.rodionov.oktan.data.database.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.rodionov.oktan.data.database.converters.FuelStationConverter
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.electric.ChargeType
import com.rodionov.oktan.data.entities.model.electric.ConnectorType

@Entity(tableName = "charging_stations")
@TypeConverters(FuelStationConverter::class)
data class ChargingStationEntity(
        @PrimaryKey
        @ColumnInfo(name = "id")
        val id: String,
        @ColumnInfo(name = "services")
        val services: List<FuelStationServices>? = null,
        @ColumnInfo(name = "brand")
        val brand: String? = null,
        @ColumnInfo(name = "charge_types")
        val chargeTypes: List<ChargeType>? = null,
        @ColumnInfo(name = "connector_types")
        val connectorTypes: List<ConnectorType>? = null,
        @ColumnInfo(name = "coordinates")
        val coordinates: Coordinates? = null,
        @ColumnInfo(name = "date_of_creation")
        val dateOfCreation: String? = null,
        @ColumnInfo(name = "creator_id")
        val creatorId: String? = null
)
package com.rodionov.oktan.data.mappers

import com.rodionov.oktan.data.database.dto.GasolineStationDto
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.data.entities.response.FuelStationResponse

object FuelStationMapper {

    fun toGasolineStationDto(gasolineStation: GasolineStation) = gasolineStation.run {
        GasolineStationDto(
                services = this.services,
                brand = this.brand,
                gasolineTypes = this.gasolineTypes,
                coordinates = this.coordinates
        )
    }

    fun toGasolineStationModel(gasolineStationDto: GasolineStationDto) = gasolineStationDto.run {
        GasolineStation(
                services = services,
                brand = brand,
                coordinates = coordinates,
                gasolineTypes = gasolineTypes,
        )
    }

    fun toGasolineStationModel(gasolineStation: FuelStationResponse) = gasolineStation.run {
        GasolineStation(
                services = services,
                brand = brand,
                coordinates = coordinates,
                gasolineTypes = listOf(),
        )
    }

}
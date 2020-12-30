package com.rodionov.oktan.data.mappers

import com.rodionov.oktan.data.database.dto.GasolineStationDto
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.data.entities.request.GasolineStationRequest
import com.rodionov.oktan.data.entities.response.FuelStationResponse
import com.rodionov.oktan.data.entities.response.GasolineStationResponse

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

    //TODO Delete after tests
    fun toGasolineStationModel(gasolineStation: FuelStationResponse) = gasolineStation.run {
        GasolineStation(
                services = services,
                brand = brand,
                coordinates = coordinates,
                gasolineTypes = listOf(),
        )
    }

    fun toGasolineStationModel(gasolineStation: GasolineStationResponse) = gasolineStation.run {
        GasolineStation(
                gs_id = gs_id,
                services = services,
                brand = brand,
                coordinates = coordinates,
                gasolineTypes = gasolineTypes,
        )
    }

    fun toGasolineStationRequest(gasolineStation: GasolineStation) = gasolineStation.run {
        GasolineStationRequest(
                services, coordinates, brand, gasolineTypes
        )
    }

}
package com.rodionov.oktan.presentation.fuel_station_list

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.cachedIn
import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.domain.FuelStationRepository
import io.reactivex.rxjava3.core.Flowable

class FuelStationListViewModel(
        private val fuelStationRepository: FuelStationRepository
) : BaseViewModel() {

    fun getGasolineStations(): Flowable<PagingData<GasolineStation>> {
//        fuelStationRepository.getGasolineStations(onSuccess = ::handleGasolineStations, onError = ::onError)
        return fuelStationRepository.getGasolineStations().map { pagingData ->
            pagingData.map(FuelStationMapper::toGasolineStationModel)
        }.cachedIn(viewModelScope)
    }

    private fun handleGasolineStations(gasolineStations: List<GasolineStation>) {
        gasolineStations.forEach {
            Log.d(TAG, "handleGasolineStations: brand = ${it.brand}")
        }
    }

}
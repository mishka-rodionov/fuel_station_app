package com.rodionov.oktan.domain

import androidx.paging.PagingData
import com.rodionov.oktan.data.database.entity.GasolineStationEntity
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import io.reactivex.rxjava3.core.Flowable

interface FuelStationRepository {

    fun getGasolineStations(onSuccess: (List<GasolineStation>) -> Unit, onError: (Throwable) -> Unit)
    fun getGasolineStations(): Flowable<PagingData<GasolineStationEntity>>

}
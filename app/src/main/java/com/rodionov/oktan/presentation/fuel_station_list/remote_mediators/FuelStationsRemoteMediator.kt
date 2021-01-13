package com.rodionov.oktan.presentation.fuel_station_list.remote_mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.data.network.api.FuelStationApi
import io.reactivex.rxjava3.core.Single

@ExperimentalPagingApi
class FuelStationsRemoteMediator(
        private val query: String,
        private val fuelStationApi: FuelStationApi,
        private val gasolineStationDao: GasolineStationDao
): RxRemoteMediator<Int, GasolineStation>() {

    override fun loadSingle(loadType: LoadType, state: PagingState<Int, GasolineStation>): Single<MediatorResult> {

    }

}
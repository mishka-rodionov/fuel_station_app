package com.rodionov.oktan.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.database.dto.GasolineStationEntity
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.data.network.api.FuelStationApi
import com.rodionov.oktan.domain.FuelStationRepository
import com.rodionov.oktan.presentation.fuel_station_list.remote_mediators.FuelStationsRemoteMediator
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

@ExperimentalPagingApi
class FuelStationRepositoryImpl(
        private val fuelStationApi: FuelStationApi,
        private val gasolineStationDao: GasolineStationDao,
        private val remoteMediator: FuelStationsRemoteMediator
) : FuelStationRepository {

    override fun getGasolineStations(onSuccess: (List<GasolineStation>) -> Unit, onError: (Throwable) -> Unit) {
        fuelStationApi.getGasolineStations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .map {
                    it.map(FuelStationMapper::toGasolineStationModel)
                }
                .subscribeBy(
                        onNext = {
                            onSuccess.invoke(it)
                        },
                        onError = {
                            onError.invoke(it)
                        }
                )
    }

    override fun getGasolineStations(): Flowable<PagingData<GasolineStationEntity>> {
        return Pager(
                config = PagingConfig(
                        pageSize = 20,
                        enablePlaceholders = true,
                        maxSize = 30,
                        prefetchDistance = 5,
                        initialLoadSize = 40
                ),
                remoteMediator = remoteMediator,
                pagingSourceFactory = { gasolineStationDao.pagingSource() }
        ).flowable
    }
}
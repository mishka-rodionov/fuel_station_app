package com.rodionov.oktan.data.repository

import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.data.network.api.FuelStationApi
import com.rodionov.oktan.domain.FuelStationRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class FuelStationRepositoryImpl(
        private val fuelStationApi: FuelStationApi
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
}
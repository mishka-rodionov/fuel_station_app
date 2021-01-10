package com.rodionov.oktan.data.repository

import android.util.Log
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.data.network.api.FuelStationApi
import com.rodionov.oktan.domain.MapRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MapRepositoryImpl(
        private val gasolineStationDao: GasolineStationDao,
        private val fuelStationsApi: FuelStationApi
) : MapRepository {

    override fun createLocalGasolineStation(gasolineStation: GasolineStation) {
        Observable.create<Unit> {
            gasolineStationDao.setGasolineStation(FuelStationMapper.toGasolineStationDto(gasolineStation = gasolineStation))
        }
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            Log.d(TAG, "createGasolineStation: save gasoline station in data base")
                        },
                        onError = {
                            Log.d(TAG, "createGasolineStation: cause = ${it.cause}")
                        }
                )
    }

    override fun createRemoteGasolineStation(gasolineStation: GasolineStation, onSuccess: (GasolineStation) -> Unit, onError: (Throwable) -> Unit) {
        fuelStationsApi.setGasolineStation(gasolineStationRequest = FuelStationMapper.toGasolineStationRequest(gasolineStation = gasolineStation))
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            onSuccess.invoke(FuelStationMapper.toGasolineStationModel(gasolineStation = it))
                            createLocalGasolineStation(FuelStationMapper.toGasolineStationModel(gasolineStation = it))
                        },
                        onError = {
                            onError.invoke(it)
                        }
                )
    }

    override fun getAllGasolineStation(onSuccess: (List<GasolineStation>) -> Unit, onError: (Throwable) -> Unit) {
        Log.d(TAG, "getAllGasolineStation: ")
        gasolineStationDao.getAllGasolineStations()
//                .subscribeOn(Schedulers.newThread())
                .map {
                    it.map(FuelStationMapper::toGasolineStationModel)
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onNext = {
                            it.forEach { station ->
                                Log.d(TAG, "getAllGasolineStation: coordinates = ${station.coordinates}")
                                onSuccess.invoke(it)
                            }
                        },
                        onError = {
                            Log.d(TAG, "getAlGasolineStation: cause = ${it.cause}")
                            onError.invoke(it)
                        }
                )
    }

//    override fun getFuelStations(onSuccess: (FuelStation) -> Unit, onError: (Throwable) -> Unit) {
//        fuelStationsApi.getFuelStations()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .map(FuelStationMapper::toGasolineStationModel).subscribeBy(
//                        onNext = {
//                            Log.d(TAG, "getFuelStations: station = $it")
//                            onSuccess.invoke(it)
//                        },
//                        onError = {
//                            Log.d(TAG, "getFuelStations: cause = ${it.cause}")
//                            onError.invoke(it)
//                        }
//                )
//    }
}
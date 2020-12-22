package com.rodionov.oktan.data.repository

import android.util.Log
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.domain.MapRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MapRepositoryImpl(
        private val gasolineStationDao: GasolineStationDao
) : MapRepository {

    override fun createGasolineStation(gasolineStation: GasolineStation) {
        Log.d(TAG, "createGasolineStation: ")
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
}
package com.rodionov.oktan.presentation.fuel_station_list.remote_mediators

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxRemoteMediator
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.database.dao.RemoteGasolineKeyDao
import com.rodionov.oktan.data.database.dto.GasolineStationEntity
import com.rodionov.oktan.data.database.dto.RemoteGasolineKeyEntity
import com.rodionov.oktan.data.entities.request.GasolineStationListRequest
import com.rodionov.oktan.data.mappers.FuelStationMapper
import com.rodionov.oktan.data.network.api.FuelStationApi
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import java.io.InvalidObjectException

@ExperimentalPagingApi
class FuelStationsRemoteMediator(
        private val fuelStationApi: FuelStationApi,
        private val gasolineStationDao: GasolineStationDao,
        private val remoteGasolineKeyDao: RemoteGasolineKeyDao
) : RxRemoteMediator<Int, GasolineStationEntity>() {

    override fun loadSingle(loadType: LoadType, state: PagingState<Int, GasolineStationEntity>): Single<MediatorResult> {
        return Single.just(loadType)
                .subscribeOn(Schedulers.io())
                .map {
                    getKeyPageData(it, state)
                }
                .flatMap { page ->
                    if (page == INVALID_PAGE) {
                        Single.just(MediatorResult.Success(endOfPaginationReached = true))
                    } else {
                        fuelStationApi.getGasolineStationList(GasolineStationListRequest(brand = "CHARGING", offset = page * LIMIT, limit = LIMIT))
                                .map {
                                    val stations = it.map(FuelStationMapper::toGasolineStationModel)
                                    stations.map(FuelStationMapper::toGasolineStationDto)
                                }
                                .map {
                                    insertToDb(page, loadType, it) }
                                .map<MediatorResult> { MediatorResult.Success(endOfPaginationReached = it.isEmpty()) }
                                .onErrorReturn { MediatorResult.Error(it) }
                    }
                }
                .onErrorReturn {
                    MediatorResult.Error(it)
                }
    }

    private fun insertToDb(page: Int, loadType: LoadType, data: List<GasolineStationEntity>): List<GasolineStationEntity> {
        if (loadType == LoadType.REFRESH) {
            remoteGasolineKeyDao.clearRemoteKeys()
            gasolineStationDao.clearAll()
        }

        val prevKey = if (page == 1) null else page - 1
        val nextKey = if (data.isEmpty()) null else page + 1
        val keys = data.map {
            RemoteGasolineKeyEntity(gasolineStationId = it.id, prevKey = prevKey, nextKey = nextKey)
        }
        remoteGasolineKeyDao.insertAll(keys)
        gasolineStationDao.insertAll(data)

        return data
    }

    fun getKeyPageData(loadType: LoadType, state: PagingState<Int, GasolineStationEntity>): Int {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)

                remoteKeys?.nextKey?.minus(1) ?: DEFAULT_PAGE_INDEX
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                        ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey ?: INVALID_PAGE
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                        ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: INVALID_PAGE
            }
        }
    }

    private fun getLastRemoteKey(state: PagingState<Int, GasolineStationEntity>):
            RemoteGasolineKeyEntity? {
        return state.pages
                .lastOrNull { it.data.isNotEmpty() }
                ?.data?.lastOrNull()
                ?.let { gasolineStation -> remoteGasolineKeyDao.getRemoteKey(gasolineStation.id) }
    }

    private fun getFirstRemoteKey(state: PagingState<Int, GasolineStationEntity>):
            RemoteGasolineKeyEntity? {
        return state.pages
                .firstOrNull() { it.data.isNotEmpty() }
                ?.data?.firstOrNull()
                ?.let { gasolineStation -> remoteGasolineKeyDao.getRemoteKey(gasolineStation.id) }
    }

    private fun getClosestRemoteKey(state: PagingState<Int, GasolineStationEntity>):
            RemoteGasolineKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                remoteGasolineKeyDao.getRemoteKey(id)
            }
        }
    }

    companion object {
        const val LIMIT = 3
        const val INVALID_PAGE = -1
        const val DEFAULT_PAGE_INDEX = 1
    }

}
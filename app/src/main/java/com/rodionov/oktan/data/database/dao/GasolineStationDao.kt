package com.rodionov.oktan.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.GasolineStationEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GasolineStationDao {

    @Query("SELECT * FROM gasoline_stations")
    fun getAllGasolineStations(): Flowable<List<GasolineStationEntity>>

    @Query("SELECT * FROM gasoline_stations WHERE id = :id")
    fun getGasolineStation(id: String): GasolineStationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGasolineStation(gasolineStationEntity: GasolineStationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<GasolineStationEntity>)

    @Query("SELECT * FROM gasoline_stations")
    fun pagingSource(): PagingSource<Int, GasolineStationEntity>

    @Query("DELETE FROM gasoline_stations")
    fun clearAll()

}
package com.rodionov.oktan.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

@Dao
interface GasolineStationDao {

    @Query("SELECT * FROM gasoline_stations")
    fun getAllGasolineStations(): Flowable<List<GasolineStationDto>>

    @Query("SELECT * FROM gasoline_stations WHERE id = :id")
    fun getGasolineStation(id: String): GasolineStationDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGasolineStation(gasolineStationDto: GasolineStationDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<GasolineStationDto>)

    @Query("SELECT * FROM gasoline_stations WHERE id LIKE :id")
    fun pagingSource(id: String): PagingSource<Int, GasolineStationDto>

    @Query("DELETE FROM gasoline_stations")
    fun clearAll()

}
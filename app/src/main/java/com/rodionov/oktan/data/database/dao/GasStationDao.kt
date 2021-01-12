package com.rodionov.oktan.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.GasStationDto
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GasStationDao {

    @Query("SELECT * FROM gas_stations")
    fun getAllGasStations(): Flowable<List<GasStationDto>>

    @Query("SELECT * FROM gas_stations WHERE id = :id")
    fun getGasStation(id: String): GasStationDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGasStation(gasStationDto: GasStationDto)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<GasStationDto>)

    @Query("SELECT * FROM gas_stations WHERE id LIKE :id")
    fun pagingSource(id: String): PagingSource<Int, GasStationDto>

    @Query("DELETE FROM gas_stations")
    fun clearAll()

}
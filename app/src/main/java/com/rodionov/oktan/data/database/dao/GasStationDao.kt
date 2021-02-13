package com.rodionov.oktan.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.entity.GasStationEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface GasStationDao {

    @Query("SELECT * FROM gas_stations")
    fun getAllGasStations(): Flowable<List<GasStationEntity>>

    @Query("SELECT * FROM gas_stations WHERE id = :id")
    fun getGasStation(id: String): GasStationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGasStation(gasStationEntity: GasStationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<GasStationEntity>)

    @Query("SELECT * FROM gas_stations WHERE id LIKE :id")
    fun pagingSource(id: String): PagingSource<Int, GasStationEntity>

    @Query("DELETE FROM gas_stations")
    fun clearAll()

}
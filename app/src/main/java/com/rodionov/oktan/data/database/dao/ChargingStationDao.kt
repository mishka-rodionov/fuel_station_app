package com.rodionov.oktan.data.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.entity.ChargingStationEntity
import io.reactivex.rxjava3.core.Flowable

@Dao
interface ChargingStationDao {

    @Query("SELECT * FROM charging_stations")
    fun getAllChargingStations(): Flowable<List<ChargingStationEntity>>

    @Query("SELECT * FROM gasoline_stations WHERE id = :id")
    fun getChargingStation(id: String): ChargingStationEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setChargingStation(chargingStationEntity: ChargingStationEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(stations: List<ChargingStationEntity>)

    @Query("SELECT * FROM charging_stations WHERE id LIKE :id")
    fun pagingSource(id: String): PagingSource<Int, ChargingStationEntity>

    @Query("DELETE FROM charging_stations")
    fun clearAll()

}
package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.ChargingStationDto
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

@Dao
interface ChargingStationDao {

    @Query("SELECT * FROM charging_stations")
    fun getAllChargingStations(): Flowable<List<ChargingStationDto>>

    @Query("SELECT * FROM gasoline_stations WHERE id = :id")
    fun getChargingStation(id: String): ChargingStationDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setChargingStation(chargingStationDto: ChargingStationDto)

}
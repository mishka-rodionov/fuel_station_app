package com.rodionov.oktan.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rodionov.oktan.data.database.dto.GasolineStationDto
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable

@Dao
interface GasolineStationDao {

    @Query("SELECT * FROM gasoline_station")
    fun getAllGasolineStations(): Flowable<List<GasolineStationDto>>

    @Query("SELECT * FROM gasoline_station WHERE id = :id")
    fun getGasolineStation(id: String): GasolineStationDto

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun setGasolineStation(gasolineStationDto: GasolineStationDto)

}
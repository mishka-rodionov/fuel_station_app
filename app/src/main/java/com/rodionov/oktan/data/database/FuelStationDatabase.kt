package com.rodionov.oktan.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodionov.oktan.data.database.FuelStationDatabase.Companion.DATABASE_VERSION
import com.rodionov.oktan.data.database.dao.GasolineStationDao
import com.rodionov.oktan.data.database.dto.GasolineStationDto

@Database(entities = [GasolineStationDto::class], version = DATABASE_VERSION, exportSchema = false)
abstract class FuelStationDatabase: RoomDatabase() {

    abstract fun gasolineStationDao(): GasolineStationDao

    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FuelStationDatabase"
        fun buildDatabase(context: Context): FuelStationDatabase =
                Room.databaseBuilder(context, FuelStationDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
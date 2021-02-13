package com.rodionov.oktan.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rodionov.oktan.data.database.FuelStationDatabase.Companion.DATABASE_VERSION
import com.rodionov.oktan.data.database.dao.*
import com.rodionov.oktan.data.database.entity.*

@Database(entities = [
    GasolineStationEntity::class,
    GasStationEntity::class,
    ChargingStationEntity::class,
    RemoteGasolineKeyEntity::class,
    BrandEntity::class], version = DATABASE_VERSION, exportSchema = false)
abstract class FuelStationDatabase : RoomDatabase() {

    abstract fun gasolineStationDao(): GasolineStationDao
    abstract fun gasStationDao(): GasStationDao
    abstract fun chargingStationDao(): ChargingStationDao
    abstract fun remoteGasolineKeyDao(): RemoteGasolineKeyDao
    abstract fun brandsDao(): BrandsDao

    companion object {
        const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "FuelStationDatabase"
        fun buildDatabase(context: Context): FuelStationDatabase =
                Room.databaseBuilder(context, FuelStationDatabase::class.java, DATABASE_NAME)
                        .fallbackToDestructiveMigration()
                        .build()
    }
}
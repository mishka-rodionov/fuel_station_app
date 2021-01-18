package com.rodionov.oktan.app.di

import android.content.Context
import com.rodionov.oktan.data.database.FuelStationDatabase
import org.koin.dsl.module

val databaseModule = module {
    single { provideDatabase(get()) }
    single { getGasolineStationDao(get()) }
    single { getGasStationDao(get()) }
    single { getChargingStationDao(get()) }
    single { getRemoteGasolineKeyDao(get()) }
}

fun provideDatabase(context: Context) = FuelStationDatabase.buildDatabase(context)
fun getGasolineStationDao(fuelStationDatabase: FuelStationDatabase) = fuelStationDatabase.gasolineStationDao()
fun getGasStationDao(fuelStationDatabase: FuelStationDatabase) = fuelStationDatabase.gasStationDao()
fun getChargingStationDao(fuelStationDatabase: FuelStationDatabase) = fuelStationDatabase.chargingStationDao()
fun getRemoteGasolineKeyDao(fuelStationDatabase: FuelStationDatabase) = fuelStationDatabase.remoteGasolineKeyDao()
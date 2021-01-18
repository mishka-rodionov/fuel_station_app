package com.rodionov.oktan.app.di

import androidx.paging.ExperimentalPagingApi
import com.rodionov.oktan.presentation.fuel_station_list.remote_mediators.FuelStationsRemoteMediator
import org.koin.dsl.module

@ExperimentalPagingApi
val remoteMediatorModule = module {
    factory<FuelStationsRemoteMediator> { FuelStationsRemoteMediator(get(), get(), get()) }
}
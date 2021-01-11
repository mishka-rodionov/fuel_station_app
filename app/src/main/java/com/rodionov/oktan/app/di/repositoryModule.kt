package com.rodionov.oktan.app.di

import com.rodionov.oktan.data.repository.FuelStationRepositoryImpl
import com.rodionov.oktan.data.repository.MapRepositoryImpl
import com.rodionov.oktan.domain.FuelStationRepository
import com.rodionov.oktan.domain.MapRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MapRepository> { MapRepositoryImpl(get(), get()) }
    single<FuelStationRepository> { FuelStationRepositoryImpl(get()) }
}
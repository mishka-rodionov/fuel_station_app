package com.rodionov.oktan.app.di

import androidx.paging.ExperimentalPagingApi
import com.rodionov.oktan.domain.repository.FuelStationRepositoryImpl
import com.rodionov.oktan.domain.repository.MapRepositoryImpl
import com.rodionov.oktan.domain.FuelStationRepository
import com.rodionov.oktan.domain.MapRepository
import org.koin.dsl.module

@ExperimentalPagingApi
val repositoryModule = module {
    single<MapRepository> { MapRepositoryImpl(get(), get()) }
    single<FuelStationRepository> { FuelStationRepositoryImpl(get(), get(), get()) }
}
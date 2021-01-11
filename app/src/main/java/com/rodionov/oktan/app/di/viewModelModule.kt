package com.rodionov.oktan.app.di

import com.rodionov.oktan.presentation.fuel_station_list.FuelStationListViewModel
import com.rodionov.oktan.presentation.map.MapViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MapViewModel(get(), get()) }
    viewModel { FuelStationListViewModel(get()) }
}
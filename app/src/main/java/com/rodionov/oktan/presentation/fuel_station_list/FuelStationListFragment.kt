package com.rodionov.oktan.presentation.fuel_station_list

import android.os.Bundle
import com.rodionov.oktan.R
import com.rodionov.oktan.app.platform.BaseFragment
import com.rodionov.oktan.app.platform.BaseViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class FuelStationListFragment: BaseFragment(R.layout.fragment_fuel_stations) {

    private val viewModel: FuelStationListViewModel by viewModel()

    override val screenViewModel by lazy { viewModel }

    override fun initViews(savedInstanceState: Bundle?) {
        viewModel.getGasolineStations()

    }

}
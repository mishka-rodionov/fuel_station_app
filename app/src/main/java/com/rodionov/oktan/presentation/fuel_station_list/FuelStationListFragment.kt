package com.rodionov.oktan.presentation.fuel_station_list

import android.os.Bundle
import com.rodionov.oktan.R
import com.rodionov.oktan.app.platform.BaseFragment
import com.rodionov.oktan.app.platform.BaseViewModel
import com.rodionov.oktan.presentation.fuel_station_list.adapters.GasolineStationAdapter
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_fuel_stations.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FuelStationListFragment : BaseFragment(R.layout.fragment_fuel_stations) {

    private val viewModel: FuelStationListViewModel by viewModel()

    override val screenViewModel by lazy { viewModel }

    private val gasolineStationAdapter by lazy {
        GasolineStationAdapter()
    }

    private val mDisposable = CompositeDisposable()


    override fun initViews(savedInstanceState: Bundle?) {
        rvFuelStations.adapter = gasolineStationAdapter
        mDisposable.add(
                viewModel.getGasolineStations().subscribe {
                    gasolineStationAdapter.submitData(lifecycle, it)
                }
        )

    }

    override fun onDestroyView() {
        mDisposable.dispose()
        super.onDestroyView()
    }

}
package com.rodionov.oktan.presentation.common.delegates

import android.util.Log
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.app.utils.Logger.TAG
import kotlinx.android.synthetic.main.item_select_fuel_station_type.view.*

fun selectFuelStationTypeAdapter() = adapterDelegateLayoutContainer<Unit, Any>(R.layout.item_select_fuel_station_type) {

    bind {
        containerView.ivGasolineStationType.setOnClickListener {
            Log.d(TAG, "ivGasolineStationType: ")
        }

        containerView.ivGasStationType.setOnClickListener {
            Log.d(TAG, "ivGasStationType: ")
        }

        containerView.ivChargingStationType.setOnClickListener {
            Log.d(TAG, "ivChargingStationType: ")
        }
    }

}

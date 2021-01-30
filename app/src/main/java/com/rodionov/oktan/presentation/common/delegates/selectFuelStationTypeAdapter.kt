package com.rodionov.oktan.presentation.common.delegates

import android.util.Log
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.FuelStationType
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import kotlinx.android.synthetic.main.item_select_fuel_station_type.view.*

fun selectFuelStationTypeAdapter(gasolineStation: GasolineStation?) = adapterDelegateLayoutContainer<Unit, Any>(R.layout.item_select_fuel_station_type) {

    bind {
        containerView.ivGasolineStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.GASOLINE
            Log.d(TAG, "ivGasolineStationType: ")
        }

        containerView.ivGasStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.GAS
            Log.d(TAG, "ivGasStationType: ")
        }

        containerView.ivChargingStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.ELECTRIC
            Log.d(TAG, "ivChargingStationType: ")
        }
    }

}

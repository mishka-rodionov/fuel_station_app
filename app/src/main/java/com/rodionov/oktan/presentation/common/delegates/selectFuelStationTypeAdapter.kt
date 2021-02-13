package com.rodionov.oktan.presentation.common.delegates

import android.util.Log
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationType
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_select_fuel_station_type.view.*

fun selectFuelStationTypeAdapter(gasolineStation: FuelStation?, clickListener: (FuelStationType) -> Unit) = adapterDelegateLayoutContainer<Unit, Any>(R.layout.item_select_fuel_station_type) {

    bind {
        containerView.ivGasolineStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.GASOLINE
            (it as CircleImageView).borderColor = context.resources.getColor(R.color.colorPrimary)
            containerView.ivGasStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            containerView.ivChargingStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            clickListener.invoke(FuelStationType.GASOLINE)
            Log.d(TAG, "ivGasolineStationType: ")
        }

        containerView.ivGasStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.GAS
            (it as CircleImageView).borderColor = context.resources.getColor(R.color.colorPrimary)
            containerView.ivGasolineStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            containerView.ivChargingStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            clickListener.invoke(FuelStationType.GAS)
            Log.d(TAG, "ivGasStationType: ")
        }

        containerView.ivChargingStationType.setOnClickListener {
            gasolineStation?.type = FuelStationType.ELECTRIC
            (it as CircleImageView).borderColor = context.resources.getColor(R.color.colorPrimary)
            containerView.ivGasolineStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            containerView.ivGasStationType.borderColor = context.resources.getColor(R.color.colorWhite)
            clickListener.invoke(FuelStationType.ELECTRIC)
            Log.d(TAG, "ivChargingStationType: ")
        }
    }

}

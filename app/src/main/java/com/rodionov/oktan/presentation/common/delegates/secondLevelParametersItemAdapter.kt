package com.rodionov.oktan.presentation.common.delegates

import android.content.Context
import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.presentation.common.delegates.SecondLevelParameters.Companion.enableService
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_second_level_parameters.view.*

fun secondLevelParametersItemAdapter(gasolineStation: GasolineStation) = adapterDelegateLayoutContainer<SecondLevelParameters, Any>(R.layout.item_second_level_parameters) {

    bind {
        containerView.ivATM.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.BANK_TERMINAL) }
        containerView.ivVacuum.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.VACUUM_CLEANER) }
        containerView.ivWC.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.WC) }
        containerView.ivWiFi.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.WI_FI) }
        containerView.ivCafe.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.CAFE) }
        containerView.ivCarWash.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.CAR_WASH) }
        containerView.ivTirePressure.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.TIRES_INFLATION) }
        containerView.chbGasoline92.setOnCheckedChangeListener { buttonView, isChecked ->
            
        }
    }

}


class SecondLevelParameters {
    companion object {
        fun enableService(it: View?, context: Context, gasolineStation: GasolineStation, service: FuelStationServices) {
            if ((it as CircleImageView).borderColor == context.resources.getColor(R.color.colorWhite)) {
                it.borderColor = context.resources.getColor(R.color.colorPrimary)
                val listService = gasolineStation.services?.toMutableList()
                listService?.add(service)
                gasolineStation.services = listService
            }
            else {
                it.borderColor = context.resources.getColor(R.color.colorWhite)
                val listService = gasolineStation.services?.toMutableList()
                listService?.remove(service)
                gasolineStation.services = listService
            }
        }
    }
}
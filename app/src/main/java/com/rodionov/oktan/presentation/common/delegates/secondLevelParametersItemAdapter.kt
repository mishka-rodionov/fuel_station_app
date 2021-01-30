package com.rodionov.oktan.presentation.common.delegates

import android.content.Context
import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.data.entities.model.FuelStation
import com.rodionov.oktan.data.entities.model.FuelStationServices
import com.rodionov.oktan.data.entities.model.gasoline.GasolineName
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import com.rodionov.oktan.data.entities.model.gasoline.GasolineType
import com.rodionov.oktan.presentation.common.delegates.SecondLevelParameters.Companion.enableService
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_second_level_parameters.view.*

fun secondLevelParametersItemAdapter(gasolineStation: GasolineStation?, clickListener: (FuelStation?) -> Unit) = adapterDelegateLayoutContainer<SecondLevelParameters, Any>(R.layout.item_second_level_parameters) {

    bind {
        containerView.ivATM.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.BANK_TERMINAL) }
        containerView.ivVacuum.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.VACUUM_CLEANER) }
        containerView.ivWC.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.WC) }
        containerView.ivWiFi.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.WI_FI) }
        containerView.ivCafe.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.CAFE) }
        containerView.ivCarWash.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.CAR_WASH) }
        containerView.ivTirePressure.setOnClickListener { enableService(it, context, gasolineStation, FuelStationServices.TIRES_INFLATION) }
        containerView.btnCreateGasolineStation.setOnClickListener {
            when {
                containerView.chbGasoline92.isChecked -> {
                    val gasolineTypes = gasolineStation?.gasolineTypes?.toMutableList()
                    gasolineTypes?.add(GasolineType(GasolineName.AI92, containerView.etPrice92.text.toString().trim().toFloat()))
                    gasolineStation?.gasolineTypes = gasolineTypes
                }
                containerView.chbGasoline95.isChecked -> {
                    val gasolineTypes = gasolineStation?.gasolineTypes?.toMutableList()
                    gasolineTypes?.add(GasolineType(GasolineName.AI95, containerView.etPrice95.text.toString().trim().toFloat()))
                    gasolineStation?.gasolineTypes = gasolineTypes
                }
                containerView.chbGasoline98.isChecked -> {
                    val gasolineTypes = gasolineStation?.gasolineTypes?.toMutableList()
                    gasolineTypes?.add(GasolineType(GasolineName.AI98, containerView.etPrice98.text.toString().trim().toFloat()))
                    gasolineStation?.gasolineTypes = gasolineTypes
                }
            }
            clickListener.invoke(gasolineStation)
        }
    }

}


class SecondLevelParameters {
    companion object {
        fun enableService(it: View?, context: Context, gasolineStation: GasolineStation?, service: FuelStationServices) {
            if ((it as CircleImageView).borderColor == context.resources.getColor(R.color.colorWhite)) {
                it.borderColor = context.resources.getColor(R.color.colorPrimary)
                val listService = gasolineStation?.services?.toMutableList()
                listService?.add(service)
                gasolineStation?.services = listService
            } else {
                it.borderColor = context.resources.getColor(R.color.colorWhite)
                val listService = gasolineStation?.services?.toMutableList()
                listService?.remove(service)
                gasolineStation?.services = listService
            }
        }
    }
}
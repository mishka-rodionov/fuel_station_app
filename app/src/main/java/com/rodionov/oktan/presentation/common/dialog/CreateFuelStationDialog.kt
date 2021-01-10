package com.rodionov.oktan.presentation.common.dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rodionov.oktan.R
import com.rodionov.oktan.app.utils.Logger.TAG
import com.rodionov.oktan.data.entities.model.*
import kotlinx.android.synthetic.main.dialog_fragment_create_fuel_station.*

class CreateFuelStationDialog(
        private val coordinates: Coordinates,
        private val createGasolineStation: (GasolineStation) -> Unit
) : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: class = ${this::class.java.simpleName}")
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.AppTheme_Dialog_Custom)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.d(TAG, "onCreateView: class = ${this::class.java.simpleName}")
        return inflater.inflate(R.layout.dialog_fragment_create_fuel_station, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tvLatitude.text = coordinates.latitude.toString()
        tvLongitude.text = coordinates.longitude.toString()
        btnDialogCancel.setOnClickListener { dismiss() }
        btnDialogOk.setOnClickListener {
            if (!validateBrand()) {
                val gasolineStation = GasolineStation(
                        id = "mobile_id",
                        type = FuelStationType.GASOLINE,
                        services = listOf(FuelStationServices.CAFE, FuelStationServices.REFUELING_SERVICES, FuelStationServices.CAR_WASH),
                        coordinates = this@CreateFuelStationDialog.coordinates,
                        brand = etBrand.text.toString(),
                        gasolineTypes = listOf(GasolineType(name = GasolineName.AI95, pricePerLiter = 45.0F, realOktanNumber = 93F)),
                        dateOfCreation = 0L,
                        creatorId = "mobile_user"
                )
                createGasolineStation.invoke(gasolineStation)
                dismiss()
            }
        }
    }

    private fun validateBrand() = etBrand.text.isNullOrBlank()
}
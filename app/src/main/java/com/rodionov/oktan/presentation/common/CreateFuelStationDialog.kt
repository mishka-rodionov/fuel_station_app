package com.rodionov.oktan.presentation.common

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.rodionov.oktan.R
import com.rodionov.oktan.app.utils.Logger.TAG
import kotlinx.android.synthetic.main.dialog_fragment_create_fuel_station.*

class CreateFuelStationDialog: DialogFragment() {
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
        tvLatitude.text = "akjsdajsdahsjd"
        tvLongitude.text = "kajsdhkasjhd"
        btnDialogCancel.setOnClickListener { dismiss() }
    }
}
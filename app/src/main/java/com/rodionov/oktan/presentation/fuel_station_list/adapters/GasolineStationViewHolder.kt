package com.rodionov.oktan.presentation.fuel_station_list.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.rodionov.oktan.R
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation

class GasolineStationViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val tvBrand = view.findViewById<TextView>(R.id.tvBrand)

    fun bind(station: GasolineStation) {
        tvBrand.text = station.brand
    }

    companion object {
        fun create(parent: ViewGroup) = GasolineStationViewHolder(
                LayoutInflater
                        .from(parent.context)
                        .inflate(R.layout.item_fuel_station, parent, false)
        )
    }
}
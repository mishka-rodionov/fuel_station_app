package com.rodionov.oktan.presentation.fuel_station_list.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation

class GasolineStationAdapter : PagingDataAdapter<GasolineStation, GasolineStationViewHolder>(COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GasolineStationViewHolder {
        return GasolineStationViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: GasolineStationViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    companion object {
        private val COMPARATOR = object : DiffUtil.ItemCallback<GasolineStation>() {
            override fun areItemsTheSame(oldItem: GasolineStation, newItem: GasolineStation): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GasolineStation, newItem: GasolineStation): Boolean {
                return oldItem.dateOfCreation == newItem.dateOfCreation
            }
        }
    }

}
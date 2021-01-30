package com.rodionov.oktan.presentation.common.delegates

import android.widget.ArrayAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.data.entities.model.gasoline.GasolineStation
import kotlinx.android.synthetic.main.item_first_level_parameters.view.*

fun itemFirstLevelAdapter(gasolineStation: GasolineStation) = adapterDelegateLayoutContainer<FirstLevelParameters, Any>(R.layout.item_first_level_parameters) {

    bind {
        containerView.actvBrand.setAdapter(
                ArrayAdapter(
                        context,
                        android.R.layout.simple_dropdown_item_1line,
                        context.resources.getStringArray(R.array.brands)
                )
        )

        containerView.actvActiveStatus.setAdapter(
                ArrayAdapter(
                        context,
                        android.R.layout.simple_dropdown_item_1line,
                        context.resources.getStringArray(R.array.statuses)
                )
        )

        containerView.actvBrand.setOnItemClickListener { parent, view, position, id ->
            gasolineStation.brand = parent.adapter.getItem(position).toString()
        }
        containerView.actvActiveStatus.setOnItemClickListener { parent, view, position, id ->
            gasolineStation.activeStatus = when (parent.adapter.getItem(position).toString()) {
                "Открыта" -> true
                else -> false
            }
        }
    }
}

class FirstLevelParameters
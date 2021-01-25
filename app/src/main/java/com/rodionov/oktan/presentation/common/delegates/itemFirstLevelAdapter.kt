package com.rodionov.oktan.presentation.common.delegates

import android.widget.ArrayAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import kotlinx.android.synthetic.main.item_first_level_parameters.view.*

fun itemFirstLevelAdapter() = adapterDelegateLayoutContainer<FirstLevelParameters, Any>(R.layout.item_first_level_parameters) {

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
    }
}

class FirstLevelParameters
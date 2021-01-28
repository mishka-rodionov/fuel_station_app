package com.rodionov.oktan.presentation.common.delegates

import android.content.Context
import android.view.View
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R
import com.rodionov.oktan.presentation.common.delegates.SecondLevelParameters.Companion.changeColor
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.item_second_level_parameters.*
import kotlinx.android.synthetic.main.item_second_level_parameters.view.*

fun secondLevelParametersItemAdapter() = adapterDelegateLayoutContainer<SecondLevelParameters, Any>(R.layout.item_second_level_parameters) {

    bind {
        containerView.ivATM.setOnClickListener { changeColor(it, context) }
        containerView.ivVacuum.setOnClickListener { changeColor(it, context) }
        containerView.ivWC.setOnClickListener { changeColor(it, context) }
        containerView.ivWiFi.setOnClickListener { changeColor(it, context) }
        containerView.ivCafe.setOnClickListener { changeColor(it, context) }
        containerView.ivCarWash.setOnClickListener { changeColor(it, context) }
        containerView.ivTirePressure.setOnClickListener { changeColor(it, context) }
    }

}



class SecondLevelParameters {
    companion object {
        fun changeColor(it: View?, context: Context) {
            if ((it as CircleImageView).borderColor == context.resources.getColor(R.color.colorWhite)) it.borderColor = context.resources.getColor(R.color.colorPrimary) else it.borderColor = context.resources.getColor(R.color.colorWhite)
        }
    }
}
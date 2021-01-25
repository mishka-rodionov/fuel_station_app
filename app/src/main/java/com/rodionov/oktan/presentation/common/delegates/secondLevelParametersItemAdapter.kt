package com.rodionov.oktan.presentation.common.delegates

import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegateLayoutContainer
import com.rodionov.oktan.R

fun secondLevelParametersItemAdapter() = adapterDelegateLayoutContainer<SecondLevelParameters, Any>(R.layout.item_second_level_parameters) {

    bind {
        
    }

}

class SecondLevelParameters
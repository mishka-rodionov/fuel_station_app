package com.rodionov.oktan.modules.map.view

import android.support.v4.app.Fragment
import android.util.Log

/**
 * Created by rodionov on 18.03.19.
 */
class MapFragment: Fragment(){

    override fun onPause() {
        super.onPause()
        Log.d("mapView", "MapFragment")
    }
}
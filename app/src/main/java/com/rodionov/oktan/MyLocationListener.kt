package com.rodionov.oktan

import android.location.Location
import android.location.LocationListener
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView

/**
 * Created by rodionov on 13.03.2019.
 */
class MyLocationListener(val text: TextView): LocationListener {

    override fun onLocationChanged(location: Location) {
//        Log.d("myLocation", "onLocationChanged ${"" + location?.latitude + " " + location?.longitude}")
        text.text = "" + location?.latitude + " " + location?.longitude
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
    }

    override fun onProviderEnabled(provider: String) {
    }

    override fun onProviderDisabled(provider: String) {
    }
}
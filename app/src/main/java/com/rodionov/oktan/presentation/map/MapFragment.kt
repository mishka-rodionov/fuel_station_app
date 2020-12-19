package com.rodionov.oktan.presentation.map

import android.os.Bundle
import android.util.Log
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.rodionov.oktan.R
import com.rodionov.oktan.app.platform.BaseFragment
import com.rodionov.oktan.app.platform.BaseViewModel
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment: BaseFragment(R.layout.fragment_map) {

    private val viewModel: MapViewModel by viewModel()

    override val screenViewModel by lazy { viewModel }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
    }

    override fun initViews(savedInstanceState: Bundle?) {
        mapView.onCreate(savedInstanceState)
        mapView?.getMapAsync { mapboxMap ->

            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                mapboxMap.addOnMapClickListener { point ->
                    Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
                    true
                }

// Map is set up and the style has loaded. Now you can add data or make other map adjustments


            }
        }
    }

}
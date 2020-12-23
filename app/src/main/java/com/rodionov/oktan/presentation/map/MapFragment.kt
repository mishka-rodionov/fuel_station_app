package com.rodionov.oktan.presentation.map

import android.os.Bundle
import android.util.Log
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.Style
import com.rodionov.oktan.R
import com.rodionov.oktan.app.platform.BaseFragment
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.presentation.common.CreateFuelStationDialog
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MapFragment : BaseFragment(R.layout.fragment_map) {

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
//                mapboxMap.addOnMapClickListener { point ->
//                    Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
//                    CreateFuelStationDialog().show(childFragmentManager, "123")
//                    true
//                }
            }

            mapboxMap.addOnMapLongClickListener { point ->
                Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
                CreateFuelStationDialog(Coordinates(latitude = point.latitude, longitude = point.longitude), ::handleCreateGasolineStation).show(childFragmentManager, "123")
                true
            }
            mapboxMap.animateCamera(CameraUpdateFactory.newCameraPosition(setCameraPosition()), 2000)

        }

    }


    private fun handleCreateGasolineStation(gasolineStation: GasolineStation) {
        viewModel.createGasolineStation(gasolineStation)
    }

    private fun setCameraPosition()  = CameraPosition.Builder()
                .target(LatLng(51.607550, 45.93207520))
                .zoom(10.0)
                .tilt(20.0)
                .build()

}
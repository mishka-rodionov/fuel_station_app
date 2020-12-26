package com.rodionov.oktan.presentation.map

import android.Manifest
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.geojson.Feature
import com.mapbox.geojson.FeatureCollection
import com.mapbox.geojson.Point
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.camera.CameraPosition
import com.mapbox.mapboxsdk.camera.CameraUpdateFactory
import com.mapbox.mapboxsdk.constants.MapboxConstants
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import com.mapbox.mapboxsdk.style.layers.SymbolLayer
import com.rodionov.oktan.R
import com.rodionov.oktan.app.extension.observe
import com.rodionov.oktan.app.platform.BaseFragment
import com.rodionov.oktan.data.entities.model.Coordinates
import com.rodionov.oktan.data.entities.model.GasolineStation
import com.rodionov.oktan.presentation.common.CreateFuelStationDialog
import kotlinx.android.synthetic.main.fragment_map.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconAllowOverlap
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconIgnorePlacement
import com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconImage
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource
import permissions.dispatcher.NeedsPermission
import permissions.dispatcher.RuntimePermissions

@RuntimePermissions
class MapFragment : BaseFragment(R.layout.fragment_map) {

    private val viewModel: MapViewModel by viewModel()

    override val screenViewModel by lazy { viewModel }

    lateinit var mapboxMap: MapboxMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Mapbox.getInstance(requireContext(), getString(R.string.mapbox_access_token))
    }

    override fun initViews(savedInstanceState: Bundle?) {
        observe(viewModel.stations, ::showGasolineStation)
        mapView.onCreate(savedInstanceState)

        mapView?.getMapAsync { mapboxMap ->

            this.mapboxMap = mapboxMap
            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
                enableLocationComponentWithPermissionCheck(it)
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

            viewModel.getAllGasolineStation()
        }

    }

    private fun showGasolineStation(stations: List<GasolineStation>?) {
        val listOfFeature = stations?.map {
            Feature.fromGeometry(
                    Point.fromLngLat(it.coordinates?.longitude ?: 0.0, it.coordinates?.latitude
                            ?: 0.0))
        }?.toMutableList() ?: mutableListOf()
        mapboxMap.setStyle(
                Style.Builder().fromUri("mapbox://styles/mapbox/cjf4m44iw0uza2spb3q0a7s41")
                        .withImage(ICON_ID, BitmapFactory.decodeResource(resources, R.drawable.mapbox_marker_icon_default))
                        .withSource(
                                GeoJsonSource(SOURCE_ID, FeatureCollection.fromFeatures(listOfFeature))
                        )
                        .withLayer(
                                SymbolLayer(LAYER_ID, SOURCE_ID).withProperties(iconImage(ICON_ID), iconAllowOverlap(true), iconIgnorePlacement(true))
                        )
        ) {
            enableLocationComponentWithPermissionCheck(it)
//                mapboxMap.addOnMapClickListener { point ->
//                    Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
//                    CreateFuelStationDialog().show(childFragmentManager, "123")
//                    true
//                }
        }
    }


    private fun handleCreateGasolineStation(gasolineStation: GasolineStation) {
        viewModel.createGasolineStation(gasolineStation)
    }

    private fun setCameraPosition() = CameraPosition.Builder()
            .target(LatLng(51.607550, 45.93207520))
            .zoom(25.5)
            .tilt(30.0)
            .build()

    @Suppress("checkPermission")
    @NeedsPermission (Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION)
    fun enableLocationComponent(loadedMapStyle: Style) {
        // Check if permissions are enabled and if not request
//        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {

            // Create and custom    ize the LocationComponent's options
            val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
                    .trackingGesturesManagement(true)
                    .accuracyColor(ContextCompat.getColor(requireContext(), R.color.colorPrimary))
                    .build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(requireContext(), loadedMapStyle)
                    .locationComponentOptions(customLocationComponentOptions)
                    .build()

            // Get an instance of the LocationComponent and then adjust its settings
            mapboxMap.locationComponent.apply {

                // Activate the LocationComponent with options
                activateLocationComponent(locationComponentActivationOptions)

                // Enable to make the LocationComponent visible
                isLocationComponentEnabled = true

                // Set the LocationComponent's camera mode
                cameraMode = CameraMode.TRACKING

                // Set the LocationComponent's render mode
                renderMode = RenderMode.COMPASS
            }
//        } else {
//            permissionsManager = PermissionsManager(this)
//            permissionsManager.requestLocationPermissions(this)
//        }
    }

    companion object {
        const val ICON_ID = "IconID"
        const val SOURCE_ID = "SourceID"
        const val LAYER_ID = "LayerID"
    }

}
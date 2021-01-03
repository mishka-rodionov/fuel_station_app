package com.rodionov.oktan.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.mapbox.mapboxsdk.Mapbox
import com.mapbox.mapboxsdk.geometry.LatLng
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.Style
import com.rodionov.oktan.R
import com.rodionov.oktan.app.platform.BaseActivity
import com.yandex.mapkit.search.SearchManager
import com.yandex.mapkit.search.Session
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : BaseActivity(R.layout.activity_main), MapboxMap.OnMapClickListener {

    private lateinit var searchManager: SearchManager
    private lateinit var searchSession: Session

    override fun onMapClick(point: LatLng): Boolean {
        Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
        return true
    }

    override fun initViews() {

    }

    override fun initToolbar() {

    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNav?.setupWithNavController(navController)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LOG_TAG", "onCreate: ")

        val host: NavHostFragment = supportFragmentManager
                .findFragmentById(R.id.mainFragmentContainer) as NavHostFragment? ?: return

        // Set up Action Bar
        val navController = host.navController
        setupBottomNavMenu(navController)
//        Mapbox.getInstance(this, getString(R.string.mapbox_access_token))
//
//        setContentView(R.layout.activity_main)
//
////        mapView = findViewById(R.id.mapView)
//        mapView?.onCreate(savedInstanceState)
//        mapView?.getMapAsync { mapboxMap ->
//
//            mapboxMap.setStyle(Style.MAPBOX_STREETS) {
//                mapboxMap.addOnMapClickListener( this )
//
//// Map is set up and the style has loaded. Now you can add data or make other map adjustments
//
//
//            }
//        }


//        MapKitFactory.setApiKey("58689044-9fe3-4559-a9f3-1052dac38fb8")
//        MapKitFactory.initialize(this)
//        SearchFactory.initialize(this)
//        searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.ONLINE)
//        setContentView(R.layout.activity_main)
//        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            Log.d("myLocation", "request permission")
//            val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
//            requestPermissions(permissions, 10)
//        } else {
//            Log.d("myLocation", "always PERMISSION_GRANTED")
//            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, MyLocationListener(coordinateText))
//            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//            var longitude = location!!.longitude
//            var latitude = location!!.latitude
//            mapView.map.move(CameraPosition(Point(latitude, longitude), 14.0F, 0.0F, 0.0F), Animation(Animation.Type.LINEAR, 0F), null)
//        }
//
//        submitQuery("АЗС")


//        Log.d("myLocation", "${mapView.map.mapObjects.userData.toString()}")
//        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        var longitude = location.longitude
//        var latitude = location.latitude

//        mapView.map.move(CameraPosition(Point(55.751574, 37.573856), 11.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 0F), null)
//        mapView.map.move(CameraPosition(Point(latitude, longitude),11.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 0F), null)
    }

//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        Log.d("myLocation", "onRequestPermissionsResult")
//        if (requestCode == 10) {
//            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                Log.d("myLocation", "onRequestPermissionsResult PERMISSION_GRANTED")
//                val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
//                if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return
//                }
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, MyLocationListener(coordinateText))
//            }
//        }
//    }

//    override fun onResume() {
//        super.onResume()
//        mapView.onResume()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        mapView.onStart()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        mapView.onStop()
//    }
//
//    override fun onPause() {
//        super.onPause()
//        mapView.onPause()
//    }
//
//    override fun onLowMemory() {
//        super.onLowMemory()
//        mapView.onLowMemory()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        mapView.onDestroy()
//    }
//
//    override fun onSaveInstanceState(outState: Bundle) {
//        super.onSaveInstanceState(outState)
//        mapView.onSaveInstanceState(outState)
//    }

//    override fun onStop() {
//        super.onStop()
////        mapView.onStop()
////        MapKitFactory.getInstance().onStop()
//    }
//
//    override fun onStart() {
//        super.onStart()
////        mapView.onStart()
////        MapKitFactory.getInstance().onStart()
//    }
}

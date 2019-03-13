package com.rodionov.oktan

import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationListener
import android.location.LocationManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.util.Log
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("58689044-9fe3-4559-a9f3-1052dac38fb8")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)
        val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if (ContextCompat.checkSelfPermission(applicationContext, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Log.d("myLocation", "request permission")
            val permissions = arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION)
            requestPermissions(permissions, 10)
        } else {
            Log.d("myLocation", "always PERMISSION_GRANTED")
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, MyLocationListener(coordinateText))
            val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
            var longitude = location.longitude
            var latitude = location.latitude
            mapView.map.move(CameraPosition(Point(latitude, longitude), 18.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 5F), null)
        }

//        val location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER)
//        var longitude = location.longitude
//        var latitude = location.latitude

//        mapView.map.move(CameraPosition(Point(55.751574, 37.573856), 11.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 0F), null)
//        mapView.map.move(CameraPosition(Point(latitude, longitude),11.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 0F), null)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        Log.d("myLocation", "onRequestPermissionsResult")
        if (requestCode == 10) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d("myLocation", "onRequestPermissionsResult PERMISSION_GRANTED")
                val locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1F, MyLocationListener(coordinateText))
            }
        }
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }
}

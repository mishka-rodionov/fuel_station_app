package com.rodionov.oktan

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        MapKitFactory.setApiKey("58689044-9fe3-4559-a9f3-1052dac38fb8")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        mapView.map.move(CameraPosition(Point(55.751574, 37.573856),11.0F, 0.0F, 0.0F), Animation(Animation.Type.SMOOTH, 0F), null)
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

package com.rodionov.oktan.presentation

import android.content.Context
import android.util.Log
import com.rodionov.oktan.R
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.Response
import com.yandex.mapkit.search.Session
import com.yandex.runtime.Error
import com.yandex.runtime.image.ImageProvider

class MySearchListener(val mapView: MapView,
                       val context: Context) : Session.SearchListener {
    override fun onSearchError(p0: Error) {
        Log.d("myLocation", "onSearchError")
    }

    override fun onSearchResponse(response: Response) {
        Log.d("myLocation", "onSearchResponse size = ${response.collection.children.size}")
        response.collection.children.forEach { Log.d("myLocation", "item = ${it.obj?.name}") }

        val mapObjects = mapView.getMap().getMapObjects()
        mapObjects.clear()

        for (searchResult in response.collection.children) {
            val resultLocation = searchResult.obj!!.geometry[0].point
            if (resultLocation != null) {
                mapObjects.addPlacemark(
                        resultLocation,
                        ImageProvider.fromResource(context, R.drawable.search_result))
            }
        }
//        Log.d("myLocation", "onSearchResponse = ${response.collection.children.get(0).obj?.descriptionText}")
    }
}
package com.rodionov.oktan.presentation.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
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
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var host: NavHostFragment
    lateinit var navController: NavController


    override fun onMapClick(point: LatLng): Boolean {
        Log.d("LOG_TAG", "onMapClick: latitude = ${point.latitude}, longitude = ${point.longitude}, altitude = ${point.altitude}")
        return true
    }

    override fun initViews() {
        host = supportFragmentManager
                .findFragmentById(R.id.mainFragmentContainer) as NavHostFragment? ?: return
        navController = host.navController
        setupBottomNavMenu(navController = navController)
        appBarConfiguration =
                AppBarConfiguration(navController.graph, drawerLayout = null)
        setupActionBar(navController, appBarConfiguration)
    }

    override fun initToolbar() {
        setSupportActionBar(toolbarMain as Toolbar)
        supportActionBar?.title = ""
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setupBottomNavMenu(navController: NavController) {
        val bottomNav = findViewById<BottomNavigationView>(R.id.bottomNavView)
        bottomNav?.setupWithNavController(navController)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("LOG_TAG", "onCreate: ")

//        val host: NavHostFragment = supportFragmentManager
//                .findFragmentById(R.id.mainFragmentContainer) as NavHostFragment? ?: return
//
//        // Set up Action Bar
//        val navController = host.navController
//        setupBottomNavMenu(navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return findNavController(R.id.mainFragmentContainer).navigateUp(appBarConfiguration)
    }

}

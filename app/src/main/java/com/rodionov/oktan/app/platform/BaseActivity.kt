package com.rodionov.oktan.app.platform

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.rodionov.oktan.R
import com.rodionov.oktan.app.extension.getCompatColor
import com.rodionov.oktan.app.extension.setStatusBarColor
import com.rodionov.oktan.app.extension.setStatusBarLightMode


abstract class BaseActivity(@LayoutRes private val layout: Int) : AppCompatActivity(layout) {

    open val screenViewModel: BaseViewModel?
        get() = null

    open val statusBarColor = R.color.colorStatusBar
    open val statusBarLightMode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setStatusBarColor(this.getCompatColor(statusBarColor))
        this.setStatusBarLightMode(statusBarLightMode)

        initToolbar()
        initViews()
    }

    abstract fun initViews()

    abstract fun initToolbar()

    protected fun setupNavigationMenu(navController: NavController, navViewId: Int) {
        val sideNavView = findViewById<NavigationView>(navViewId)
        sideNavView?.setupWithNavController(navController)
    }

    protected fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupActionBarWithNavController(navController, appBarConfig)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    protected fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

}
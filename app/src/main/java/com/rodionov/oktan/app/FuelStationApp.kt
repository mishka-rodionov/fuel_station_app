package com.rodionov.oktan.app

import android.app.Application
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.rodionov.oktan.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class FuelStationApp : Application() {

    @ExperimentalPagingApi
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@FuelStationApp)
            modules(listOf(appModule, networkModule, databaseModule, viewModelModule, repositoryModule, remoteMediatorModule))
        }
    }

    companion object {
        @Volatile
        lateinit var context: Context
    }

}
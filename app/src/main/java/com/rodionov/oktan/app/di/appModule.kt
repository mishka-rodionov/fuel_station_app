package com.rodionov.oktan.app.di

import android.content.Context
import com.rodionov.oktan.app.utils.LocationProvider
import org.koin.dsl.module

val appModule = module {
    single { provideLocationProvider(get()) }
}

fun provideLocationProvider(context: Context): LocationProvider {
    return LocationProvider(context = context)
}
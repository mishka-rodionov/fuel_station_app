package com.rodionov.oktan.app.di

import com.rodionov.oktan.data.repository.MapRepositoryImpl
import com.rodionov.oktan.domain.MapRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<MapRepository> { MapRepositoryImpl(get()) }
}
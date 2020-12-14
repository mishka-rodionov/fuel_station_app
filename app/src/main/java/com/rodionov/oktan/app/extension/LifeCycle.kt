package com.rodionov.oktan.app.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.rodionov.oktan.app.platform.Event
import com.rodionov.oktan.app.platform.EventObserver

fun <T : Any?, L : LiveData<T>> Fragment.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(viewLifecycleOwner, Observer(body))

fun <T : Any?, L : LiveData<Event<T>>>
        Fragment.observeEvent(liveData: L, body: (T) -> Unit) =
    liveData.observe(viewLifecycleOwner, EventObserver(body))

//fun <L : LiveData<Failure>> Fragment.failure(liveData: L, body: (Failure?) -> Unit) =
//    liveData.observe(viewLifecycleOwner, Observer(body))
//
fun <T : Any?, L : LiveData<T>> AppCompatActivity.observe(liveData: L, body: (T?) -> Unit) =
    liveData.observe(this, Observer(body))
//
//fun <T : Any?, L : LiveData<Event<T>>> AppCompatActivity.observeEvent(
//    liveData: L,
//    body: (T) -> Unit
//) =
//    liveData.observe(this, EventObserver(body))
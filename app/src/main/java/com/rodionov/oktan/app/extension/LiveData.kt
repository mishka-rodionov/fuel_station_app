package com.rodionov.oktan.app.extension

import androidx.lifecycle.MutableLiveData

fun <T : Any?> MutableLiveData<T>.refresh() = apply { setValue(value) }

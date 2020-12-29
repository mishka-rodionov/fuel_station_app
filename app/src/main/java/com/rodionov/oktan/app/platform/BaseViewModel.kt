package com.rodionov.oktan.app.platform

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rodionov.oktan.app.utils.Logger.TAG
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val mainState = MutableLiveData<Event<State>>()
    val fragmentNavigation = MutableLiveData<Event<NavigationEvent>>()

    fun navigate(navigationEvent: NavigationEvent) {
        fragmentNavigation.value = Event(navigationEvent)
    }

    fun onExit() {
        navigate(NavigationEvent.Exit)
    }

    fun onError(throwable: Throwable) {
        Log.d(TAG, "onError: cause = ${throwable.cause}")
    }

    protected fun handleState(state: State) {
        mainState.value = Event(state)
    }

    protected fun launch(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) { func.invoke() }

    protected fun launchMain(func: suspend () -> Unit) = viewModelScope.launch(Dispatchers.Main) { func.invoke() }
}
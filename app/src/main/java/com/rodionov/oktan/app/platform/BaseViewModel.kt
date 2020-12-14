package com.rodionov.oktan.app.platform

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    protected fun handleState(state: State) {
        mainState.value = Event(state)
    }

    protected fun launch(func: suspend () -> Unit) =
        viewModelScope.launch(Dispatchers.IO) { func.invoke() }

    protected fun launchMain(func: suspend () -> Unit) = viewModelScope.launch(Dispatchers.Main) { func.invoke() }
}
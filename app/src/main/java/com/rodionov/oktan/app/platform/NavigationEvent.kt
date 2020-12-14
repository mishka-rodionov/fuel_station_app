package com.rodionov.oktan.app.platform

import android.os.Bundle
import androidx.navigation.NavOptions

sealed class NavigationEvent {

    //назад
    object Exit : NavigationEvent()

    //очистить стек текущей вкладки
    object ClearStack : NavigationEvent()

    //достать фрагменты
    data class PopFragments(val count: Int) : NavigationEvent()

    //переключить вкладку
    data class SwitchTab(val tabPosition: Int) : NavigationEvent()

    //открыть новый фрагмент
    data class PushFragment(
        val action: Int,
        val bundle: Bundle? = null,
        val navOptions: NavOptions? = null,
        val clearStack: Boolean = false
    ) : NavigationEvent()
}
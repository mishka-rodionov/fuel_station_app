package com.rodionov.oktan.app.platform

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.rodionov.oktan.R
import com.rodionov.oktan.app.extension.getCompatColor
import com.rodionov.oktan.app.extension.observeEvent
import com.rodionov.oktan.app.extension.setStatusBarColor
import com.rodionov.oktan.app.extension.setStatusBarLightMode

abstract class BaseFragment(@LayoutRes private val contentLayoutId: Int) :
    Fragment(contentLayoutId) {

    open val supportFragmentManager
        get() = requireActivity().supportFragmentManager

    open val screenViewModel: BaseViewModel?
        get() = null

    open val statusBarColor = R.color.colorStatusBar
    open val statusBarLightMode = false

    open val toolbarTitle: Int? = null
    open val toolbarDrawableClose: Int? = null

    protected var navigationController: NavController? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navigationController = findNavController()
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.setStatusBarColor(requireActivity().getCompatColor(statusBarColor))
        activity?.setStatusBarLightMode(statusBarLightMode)

//        if (showBottomNavWhenFragmentLaunch) {
//            fragmentNavigation?.showBottomNavigation()
//        } else {
//            fragmentNavigation?.hideBottomNavigation()
//        }

//        setToolbarTitle()
        initViews(savedInstanceState)
        observeBaseLiveData()
    }


    abstract fun initViews(savedInstanceState: Bundle? = null)


    override fun onStop() {
        super.onStop()
        hideSoftKeyboard()
    }

    open fun observeBaseLiveData() {
        screenViewModel?.let { vm ->
//            observeEvent(vm.mainState, ::handleState)
//            observeEvent(vm.message, ::showToast)
            observeEvent(vm.fragmentNavigation, ::handleFragmentNavigation)
        }
    }

    private fun handleFragmentNavigation(event: NavigationEvent?) {
        when (event) {
            NavigationEvent.ClearStack -> navigationController
            NavigationEvent.Exit -> navigationController?.popBackStack()
            is NavigationEvent.PushFragment -> {
                if (event.clearStack) {
                    while (navigationController?.popBackStack() == true) {
                        navigationController?.popBackStack()
                    }
                }
                if (event.navOptions != null)
                    navigationController?.navigate(event.action, event.bundle, event.navOptions)
                else
                    navigationController?.navigate(event.action, event.bundle)
            }
        }
    }


    protected fun hideSoftKeyboard() {
        view?.let {
            val imm =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }
    }

    open fun onBackPressed(): Boolean = false

    companion object {
        const val PIN_TYPE = "PinType"
    }

}
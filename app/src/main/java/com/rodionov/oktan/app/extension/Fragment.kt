package com.rodionov.oktan.app.extension

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.showToast(text: String?) {
    text?.let {
        Toast.makeText(context, text, Toast.LENGTH_LONG).show()
    }
}
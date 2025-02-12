package com.emanuel.weatherapp.presentation.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethod.SHOW_FORCED
import android.view.inputmethod.InputMethodManager
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment

object FragmentExt {

    fun Fragment.hideKeyboard(view: View?) {
        val inputManager = getInputMethodManager()
        view?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                view.windowInsetsController?.hide(WindowInsetsCompat.Type.ime())
                view.clearFocus()
            } else {
                inputManager.hideSoftInputFromWindow(
                    view.applicationWindowToken,
                    0
                )
            }
        }
    }

    fun Fragment.showKeyboard(view: View?) {
        val inputManager = getInputMethodManager()
        view?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                view.requestFocus()
                view.windowInsetsController?.show(WindowInsetsCompat.Type.ime())
            } else {
                view.postDelayed(
                    {
                        view.requestFocus()
                        inputManager.showSoftInput(view, SHOW_FORCED)
                    },
                    100
                )
            }
        }
    }

    fun Fragment.getInputMethodManager() =
        activity?.getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
}
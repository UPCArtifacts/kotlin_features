package com.boardgamegeek.extensions

import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog

fun AlertDialog.requestFocus(view: View? = null) { //#extension_function,func_with_default_value
    view?.requestFocus() //#safe_call
    window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE) //#safe_call
}
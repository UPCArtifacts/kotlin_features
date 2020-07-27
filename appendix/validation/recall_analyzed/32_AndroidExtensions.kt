@file:Suppress("NOTHING_TO_INLINE", "FunctionMinLength")

package me.proxer.app.util.extension

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.AttrRes
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.children

@ColorInt
inline fun Context.resolveColor( //#inline_func,func_with_default_value,extension_function
    @AttrRes res: Int,
    resourceTheme: Resources.Theme = theme,
    resolveRefs: Boolean = true
) = TypedValue()
    .apply { //#lambda
        if (!resourceTheme.resolveAttribute(res, this, resolveRefs)) {
            error("Could not resolve $res") //#string_template
        }
    }
    .let { //#lambda
        when { //#when_expr
            it.resourceId != 0 -> ContextCompat.getColor(this, it.resourceId)
            it.data != 0 -> it.data
            else -> error("Could not resolve $res")//#string_template
        }
    }

@CheckResult
inline fun <reified T : Any> Context.intentFor(vararg params: Pair<String, Any?>): Intent {//#inline_func,extension_function
    val intent = Intent(this, T::class.java) //#inference
    params.forEach { intent.putExtras(bundleOf(it)) } //#lambda
    return intent
}

inline fun <reified T : Activity> Context.startActivity(vararg params: Pair<String, Any?>) = //#inline_func,extension_function
    startActivity(intentFor<T>(*params))

inline fun Context.toast(message: Int, duration: Int = Toast.LENGTH_LONG): Toast = Toast //#inline_func,extension_function,func_with_default_value
    .makeText(this, message, duration)
    .apply { show() } //#lambda

inline fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG): Toast = Toast //#inline_func,extension_function,func_with_default_value
    .makeText(this, message, duration)
    .apply { show() } //#lambda

val ViewGroup.recursiveChildren: Sequence<View> 
    get() = children.flatMap { //#lambda
        if (it is ViewGroup) { //#smart_cast
            sequenceOf(it) + it.recursiveChildren
        } else {
            sequenceOf(it)
        }
    }

inline fun Intent.newTask(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_NEW_TASK) } //#inline_func,lambda,extension_function
inline fun Intent.clearTop(): Intent = apply { addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP) } //#inline_func,lambda,extension_function
inline fun Context.dip(value: Int): Int = (value * resources.displayMetrics.density).toInt() //#inline_func,extension_function
inline fun Context.dip(value: Float): Int = (value * resources.displayMetrics.density).toInt() //#inline_func,extension_function
inline fun Context.sp(value: Int): Int = (value * resources.displayMetrics.scaledDensity).toInt() //#inline_func,extension_function
inline fun Context.sp(value: Float): Int = (value * resources.displayMetrics.scaledDensity).toInt() //#inline_func,extension_function

inline fun View.dip(value: Int): Int = context.dip(value) //#inline_func,extension_function
inline fun View.dip(value: Float): Int = context.dip(value) //#inline_func,extension_function
inline fun View.sp(value: Int): Int = context.sp(value) //#inline_func,extension_function
inline fun View.sp(value: Float): Int = context.sp(value) //#inline_func,extension_function

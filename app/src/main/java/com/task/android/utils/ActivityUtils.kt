package com.task.android.utils

import android.app.Activity
import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.task.android.R


// This is general utils (not depending on the project) and can easily implement in other projects


fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

inline fun <reified A : Activity> Activity.navigate(builder: Intent.() -> Unit = {}) {

    val targetIntent = Intent(this, A::class.java).apply(builder)

    val options = ActivityOptions.makeSceneTransitionAnimation(this)
    startActivity(targetIntent/*, options.toBundle()*/)
    overridePendingTransition(R.anim.enter_anim, R.anim.exit_anim)

}

inline fun <reified A : Activity> Fragment.navigate(builder: Intent.() -> Unit = {}) {
    requireActivity().navigate<A>(builder)
}

fun Context.showToast(message: String, lengthLong: Boolean = false) {
    val length = if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, message, length).show()
}

fun Context.showToast(@StringRes resId: Int, lengthLong: Boolean = false) {
    val length = if (lengthLong) Toast.LENGTH_LONG else Toast.LENGTH_SHORT
    Toast.makeText(this, resId, length).show()
}

fun Activity.showToast(message: String, lengthLong: Boolean = false) {
    application.showToast(message, lengthLong)
}

fun Activity.showToast(@StringRes resId: Int, lengthLong: Boolean = false) {
    application.showToast(resId, lengthLong)
}

fun Fragment.showToast(message: String, lengthLong: Boolean = false) {
    requireContext().showToast(message, lengthLong)
}

fun Fragment.showToast(@StringRes resId: Int, lengthLong: Boolean = false) {
    requireContext().showToast(resId, lengthLong)
}


fun String?.isValidEmail(): Boolean {
    return (!TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this.toString()).matches())
}

fun Activity.hideSoftKeyboard() {
    currentFocus?.apply {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
        imm!!.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun Fragment.hideSoftKeyboard() {
    requireActivity().hideSoftKeyboard()
}


fun Fragment.navigate(@IdRes resId: Int) {

    val navOptions = NavOptions.Builder().apply {
        setEnterAnim(R.anim.enter_anim)
        setExitAnim(R.anim.exit_anim)
        setPopEnterAnim(R.anim.pop_enter_anim)
        setPopExitAnim(R.anim.pop_exit_anim)
    }.build()

    findNavController().navigate(resId, null, navOptions)

}

/**
 * This method converts device specific pixels to density independent pixels.
 *
 * @param px      A value in px (pixels) unit. Which we need to convert into db
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent dp equivalent to px value
 */
fun convertPxToDp(context: Context, px: Float): Float {
    return px / context.resources.displayMetrics.density
}

// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -

/**
 * This method converts dp unit to equivalent pixels, depending on device density.
 *
 * @param dp      A value in dp (density independent pixels) unit. Which we need to convert into pixels
 * @param context Context to get resources and device specific display metrics
 * @return A float value to represent px equivalent to dp depending on device density
 */
fun convertDpToPx(context: Context, dp: Float): Float {
    return dp * context.resources.displayMetrics.density
}



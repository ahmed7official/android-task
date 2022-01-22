package com.task.android.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch


fun AppCompatActivity.launchLifecycleScope(block: suspend () -> Unit) {

    lifecycleScope.launch {
        block()
    }

}


fun Fragment.launchViewLifecycleScope(block: suspend () -> Unit) {

    viewLifecycleOwner.lifecycleScope.launch {
        block()
    }

}

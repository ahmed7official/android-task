package com.task.android.utils

fun interface OnClick<T> {

    operator fun invoke(item: T, position: Int)

}
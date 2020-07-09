package com.vin.animationdeo.util

import android.content.Context
import android.util.TypedValue

class Utility private constructor() {
    companion object {
        fun toDP(context: Context, value: Int): Int {
            return TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                value.toFloat(), context.resources.displayMetrics
            ).toInt()
        }
    }
}
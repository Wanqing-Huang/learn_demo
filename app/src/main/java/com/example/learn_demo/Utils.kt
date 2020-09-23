package com.example.learn_demo

import android.content.Context

object Utils {
    fun dip2px(context: Context, dp: Float): Int {
        val density: Float = context.resources.displayMetrics.density
        return (dp * density + 0.5f * if (dp >= 0) 1 else -1).toInt()
    }
}
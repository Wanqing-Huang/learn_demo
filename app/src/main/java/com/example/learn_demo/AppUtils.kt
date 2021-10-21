package com.example.learn_demo

import android.annotation.SuppressLint
import android.content.Context

@SuppressLint("StaticFieldLeak")
object AppUtils {

    lateinit var context: Context
        private set

    fun initWith(context: Context) {
        this.context = context
    }
}
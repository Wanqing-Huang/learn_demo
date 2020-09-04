package com.example.learnsample

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

/**
 * @author vianhuang
 * @date 2020/3/31 11:31 AM
 */
object ThreadLocalTest {
    private val dateFormat1 = object : ThreadLocal<SimpleDateFormat>(){
        override fun initialValue(): SimpleDateFormat? {
            return SimpleDateFormat("mm:ss",  Locale.getDefault())
        }
    }

    private val dateFormat2 = object : ThreadLocal<SimpleDateFormat>(){
        override fun initialValue(): SimpleDateFormat? {
            return SimpleDateFormat("MM-dd HH:mm:ss.SSS ", Locale.getDefault())
        }
    }

    public fun test(){
        val now = Date(System.currentTimeMillis())
        var date = dateFormat1.get()?.format(now)
        Log.d("vianhuang", "format by dateFormat1, date = $date")
        date = dateFormat2.get()?.format(now)
        Log.d("vianhuang", "format by dateFormat2, date = $date")
    }




}
package com.example.learn_demo.alarmmanager

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(AlarmManagerTestActivity.TAG, "on receive alarm broadcast. do something...")
    }
}
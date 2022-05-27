package com.example.learn_demo.alarmmanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_alarmmanager.*

/**
 * 问题1. API19 应用程序被Kill掉后,设置的闹钟会失效
 *      可以考虑对应用程序进行保活，定期检查闹钟是否失效
 * 问题2. 手机重启后设置的闹钟也会失效
 *      需要监听手机重启广播，重新设置闹钟
 */
class AlarmManagerTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "alarm"
    }

    private lateinit var alarmMgr: AlarmManager
    private lateinit var alarmIntent: PendingIntent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarmmanager)

        alarmMgr = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        //AlarmManager不仅可以唤醒服务，也可以发送广播，也可以调起Activity；
        //将上述PendingIntent.getService()修改成对应的PendingIntent.getActivity()或者PendingIntent.getBroadcast()方法即可
        alarmIntent =
            PendingIntent.getBroadcast(
                this,
                0,
                Intent(this, AlarmReceiver::class.java),
                0
            )

        btn_start_alarm.setOnClickListener {
            startAlarm()
        }

        btn_stop_alarm.setOnClickListener {
            stopAlarm()
        }
    }


    private fun startAlarm() {
        Log.d(TAG, "start one time alarm...")
        alarmMgr.setRepeating(
            AlarmManager.ELAPSED_REALTIME_WAKEUP, //RTC
            SystemClock.elapsedRealtime() + 30 * 1000, //1 minute后触发
            60 * 1000,
            alarmIntent
        )
    }

    private fun stopAlarm() {
        Log.d(TAG, "cancel one time alarm...")
        alarmMgr.cancel(alarmIntent)
    }

}
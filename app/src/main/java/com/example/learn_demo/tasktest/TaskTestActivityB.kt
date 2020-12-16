package com.example.learn_demo.tasktest

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_task_test.*
import java.lang.ref.WeakReference

/**
 * @author vianhuang
 * @date 2020/12/10 2:56 PM
 */
class TaskTestActivityB: AppCompatActivity() {
    companion object{
        var activityB : WeakReference<Activity>? = null
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("vian", "TaskTestActivityB onCreate")

        activityB = WeakReference(this)

        title = "TaskTestActivityB"
        setContentView(R.layout.activity_task_test)

        btn0.text = "go to ActivityA"
        btn0.setOnClickListener {
            startActivity(Intent(this, TaskTestActivityA::class.java))
        }

        btn1.text = "go to ActivityC"
        btn1.setOnClickListener {
            startActivityForResult(Intent(this, TaskTestActivityC::class.java), 1002)
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("vian", "TaskTestActivityB onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("vian", "TaskTestActivityB onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("vian", "TaskTestActivityB onActivityResult")
    }
}
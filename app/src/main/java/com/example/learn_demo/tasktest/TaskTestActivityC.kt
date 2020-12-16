package com.example.learn_demo.tasktest

import android.app.ActivityManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.MainActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_task_test.*

/**
 * @author vianhuang
 * @date 2020/12/10 2:56 PM
 */
class TaskTestActivityC: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("vian", "TaskTestActivityC onCreate")

        title = "TaskTestActivityC"
        setContentView(R.layout.activity_task_test)

        btn0.text = "go to ActivityA"
        btn0.setOnClickListener {
            startActivity(Intent(this, TaskTestActivityA::class.java))
        }

        btn1.text = "go to ActivityB"
        btn1.setOnClickListener {
            startActivity(Intent(this, TaskTestActivityB::class.java))
        }

        btn2.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))

//            (getSystemService(Context.ACTIVITY_SERVICE) as? ActivityManager)?.let {
//                val info = it.getRunningTasks(1)[0]
//                val packageName = info.topActivity?.packageName
//                val topclassName = info.topActivity?.className
//                val baseclassname = info.baseActivity?.className
//                val acitivitynum = info.numActivities
//                Log.d("vian", "getRunningTasks packageName=$packageName, topclassName=$topclassName, baseclassname=$baseclassname, acitivitynum=$acitivitynum.")
//            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d("vian", "TaskTestActivityC onNewIntent")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("vian", "TaskTestActivityC onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("vian", "TaskTestActivityC onActivityResult")
    }
}
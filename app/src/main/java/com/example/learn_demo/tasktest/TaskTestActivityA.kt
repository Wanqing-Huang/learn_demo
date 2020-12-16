package com.example.learn_demo.tasktest

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_task_test.*

/**
 * @author vianhuang
 * @date 2020/12/10 2:56 PM
 */
class TaskTestActivityA: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("vian", "TaskTestActivityA onCreate")

        title = "TaskTestActivityA"
        setContentView(R.layout.activity_task_test)

        btn0.text = "go to ActivityB"
        btn0.setOnClickListener {
            startActivityForResult(Intent(this, TaskTestActivityB::class.java), 1001)
        }

        btn1.text = "go to ActivityC"
        btn1.setOnClickListener {
            startActivity(Intent(this, TaskTestActivityC::class.java))
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("vian", "TaskTestActivityA onDestroy")
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("vian", "TaskTestActivityA onActivityResult")
    }
}
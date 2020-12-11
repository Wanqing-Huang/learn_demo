package com.example.learn_demo.tasktest

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_task_test.*

/**
 * @author vianhuang
 * @date 2020/12/10 2:56 PM
 */
class TaskTestActivityC: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
    }
}
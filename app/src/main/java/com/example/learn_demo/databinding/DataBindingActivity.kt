package com.example.learn_demo.databinding

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_task_test.*

/**
 * @author vianhuang
 * @date 2020/12/10 2:56 PM
 */
class DataBindingActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("vian", "TaskTestActivityA onCreate")

        title = "DataBindingActivity"
        DataBindingUtil.setContentView<ActivityDatabindingBinding>(this, R.layout.activity_databinding)
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
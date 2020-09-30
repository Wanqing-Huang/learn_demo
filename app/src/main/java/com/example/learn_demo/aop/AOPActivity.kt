package com.example.learn_demo.aop

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_aop.*

/**
 * @author vianhuang
 * @date 2020/9/18 3:44 PM
 */
class AOPActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aop)

        btn_aspectj_test.setOnClickListener {
            costFunc()
        }
    }

    @BehaviorTrace("costFunc")
    fun costFunc() {
        Log.i("aop", "start cost function...")
        //模拟执行耗费时间的代码
        SystemClock.sleep(20)
        Log.i("aop", "end cost function...")
    }
}
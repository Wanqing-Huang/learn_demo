package com.example.learn_demo.aop

import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import com.example.learn_demo.aop.aspectj.BehaviorTrace
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
            aspectTest()
        }

        btn_asm_test.setOnClickListener {
            asmTest()
        }
    }

    @BehaviorTrace("costFunc")
    fun aspectTest() {
        Log.i("aop", "start cost function...")
        //模拟执行耗费时间的代码
        SystemClock.sleep(20)
        Log.i("aop", "end cost function...")
    }

    fun asmTest(){
        Log.i("aop", "start asm test...")
        //模拟执行耗费时间的代码
        SystemClock.sleep(20)
        Log.i("aop", "end asm test...")
    }
}
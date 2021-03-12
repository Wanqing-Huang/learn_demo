package com.example.learn_demo.lock

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_lock.*

/**
 * @author vianhuang
 * @date 2021/3/12 3:33 PM
 */
class LockTestActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "LockTestActivity"
        setContentView(R.layout.activity_lock)

        val reentrantLock = ReentrantLockTest()
        btn_reentrant_test.setOnClickListener {
            reentrantLock.test()
        }

        val readWriteLockTest = ReadWriteLockTest()
        btn_readwrite_test.setOnClickListener {
            readWriteLockTest.test()
        }
    }
}
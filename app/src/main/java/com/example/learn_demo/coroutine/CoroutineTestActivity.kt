package com.example.learn_demo.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_channel_test.*
import kotlinx.android.synthetic.main.activity_coroutine_test.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.suspendCoroutine
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2020/12/17 6:40 PM
 */
class CoroutineTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        test()
        setContentView(R.layout.activity_coroutine_test)

        btn_create_coroutine.setOnClickListener {
            createCoroutine()
        }
    }

    private fun createCoroutine(): Job{
        return GlobalScope.launch {
            doHeavyTask0()
        }
    }


    private fun test() {
        GlobalScope.launch {
            val deffer0 = async { doHeavyTask0() }
            Log.d("vian", "after async task0.")

            val deffer1 = async { doHeavyTask1() }
            Log.d("vian", "after async task1.")

            deffer0.await()
            Log.d("vian", "after await task0.")

            deffer1.await()
            Log.d("vian", "after await task1.")
        }


    }

    private suspend fun doHeavyTask0(): Boolean {
        delay(100000000)
        return true
    }

    private suspend fun doHeavyTask1(): Boolean {
        delay(1000)
        return true
    }

    private suspend fun realDoSomething() = suspendCoroutine<Boolean> {
        it.resumeWith(Result.success(true))
    }

    private fun foo() {

    }


}
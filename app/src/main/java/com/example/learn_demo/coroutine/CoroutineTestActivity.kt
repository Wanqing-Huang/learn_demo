package com.example.learn_demo.coroutine

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import com.example.learn_demo.R
import com.example.learn_demo.coroutine.scope.CustomCoroutineScope
import com.example.learn_demo.coroutine.scope.SafeCoroutineScope
import kotlinx.android.synthetic.main.activity_coroutine_test.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import java.lang.IllegalStateException
import kotlin.coroutines.CoroutineContext

/**
 * @author vianhuang
 * @date 2020/12/17 6:40 PM
 */
class CoroutineTestActivity : AppCompatActivity() {
    private val mChannel = Channel<Unit>(Channel.CONFLATED)
    private val mCoroutineScope: CoroutineScope by lazy { GlobalScope }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coroutine_test)

        btn_create_coroutine.setOnClickListener {
            mCoroutineScope.launch {
                mChannel.receive()
            }
        }

        btn_close_coroutine.setOnClickListener {
            mCoroutineScope.launch(Dispatchers.Default) {
                try {
                    Log.d("vian", "before send.")
                    mChannel.send(Unit)
                    Log.d("vian", "after send.")
                    throw IllegalStateException("throw a exception")
                } catch (e: Exception) {
                    Log.d("vian", "handle coroutine exception.")
                }
            }
        }

        btn_start_scope.setOnClickListener {
            (mCoroutineScope as? CustomCoroutineScope)?.start()
        }

        btn_cancel_scope.setOnClickListener {
            (mCoroutineScope as? CustomCoroutineScope)?.close()
        }

        btn_print_job.setOnClickListener {
            mCoroutineScope.coroutineContext[Job]?.let {
                printJob(it)
            } ?: let {
                Log.d("vian", "Job not found.")
            }
        }
    }

    private fun printJob(job: Job) {
        val count = job.children.count()
        Log.d(
            "vian",
            "Job[${job.hashCode()}] isActive = ${job.isActive}, isCompleted = ${job.isCompleted}, isCancelled = ${job.isCancelled}, childrenCount = $count."
        )
        job.children.forEach {
            printJob(it)
        }
    }

    /**
     * try-catch是无法捕获回调中的异常的，因此使用在coroutine外部使用try-catch是无效的。
     * 但是在协程内部调用try-catch是有效的，因为协程会为invokeSuspend方法中的每个分支加上try-catch
     */
    private fun tryCatchTest(view: View) {
        //无效
        try {
            view.postDelayed({ throw IllegalStateException("throw a exception") }, 1000)
        } catch (e: Exception) {
            Log.e("vian", "handle callback exception.")
        }

        //无效
        try {
            mCoroutineScope.launch {
                delay(10000)
                throw IllegalStateException("throw a exception")
            }
        } catch (e: Exception) {
            Log.e("vian", "handle coroutine exception.")
        }

        //有效
        mCoroutineScope.launch(Dispatchers.Unconfined) {
            try {
                delay(10000)
                throw IllegalStateException("throw a exception")
            } catch (e: Exception) {
                Log.d("vian", "handle coroutine exception.")
            }
        }
    }


}
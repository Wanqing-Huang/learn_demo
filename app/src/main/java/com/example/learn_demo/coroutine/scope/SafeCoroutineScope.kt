package com.example.learn_demo.coroutine.scope

import android.util.Log
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.AbstractCoroutineContextElement
import kotlin.coroutines.CoroutineContext

/**
 * @author vianhuang
 * @date 2020/12/19 4:46 PM
 *
 * 1. 每次返回的coroutineContext必须是同一个，不然之前绑定的JobTree将会丢失
 * 2. SupervisorJob的一个子job异常并不会影响其他子job的执行
 * 3. CoroutineExceptionHandler是coroutine提供的异常处理器，可以对整个scope设置，也可以对单个coroutine设置
 */
class SafeCoroutineScope : CoroutineScope {
    private val parentCoroutineContext by lazy { buildContext() }

    /**
     * 每次返回的coroutineContext必须是同一个，不然之前绑定的JobTree将会丢失
     */
    override val coroutineContext: CoroutineContext
        get() = parentCoroutineContext

    private fun buildContext(): CoroutineContext {
        return SupervisorJob() + Dispatchers.Main + mHandler
    }

    var listener: CoroutineExceptionListener? = null

    /**
     * 系统封装的CoroutineExceptionHandler
     * 可以给CoroutineScope设置，也可以给单个Coroutine设置
     */
    private val mHandler = CoroutineExceptionHandler { context, exception ->
        Log.d("vian", "handle coroutine exception. msg = ${exception.message}")
        exception.printStackTrace()
        listener?.handleException(context, exception)
    }

    /**
     * 自己封装的CoroutineExceptionHandler
     * 可以给CoroutineScope设置，也可以给单个Coroutine设置
     */
    class UncaughtCoroutineExceptionHandler(private val listener: CoroutineExceptionListener? = null) :
        CoroutineExceptionHandler,
        AbstractCoroutineContextElement(CoroutineExceptionHandler) {
        override fun handleException(context: CoroutineContext, exception: Throwable) {
            Log.d("vian", "handle coroutine exception. msg = ${exception.message}")
            exception.printStackTrace()
            listener?.handleException(context, exception)
        }
    }

    interface CoroutineExceptionListener {
        fun handleException(context: CoroutineContext, exception: Throwable)
    }
}
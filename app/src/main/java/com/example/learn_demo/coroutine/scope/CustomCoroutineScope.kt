package com.example.learn_demo.coroutine.scope

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * @author vianhuang
 * @date 2020/12/19 3:44 PM
 */
class CustomCoroutineScope : CoroutineScope {
    private var parentCoroutineContext = buildContext().apply {
        cancel()
    }

    override val coroutineContext: CoroutineContext
        get() = parentCoroutineContext

    /**
     * 启动scope
     */
    fun start(){
        parentCoroutineContext = buildContext()
    }

    /**
     * 关闭scope
     */
    fun close(){
        parentCoroutineContext.cancel()
    }

    private fun buildContext(): CoroutineContext {
        return SupervisorJob() + Dispatchers.Main.immediate
    }
}
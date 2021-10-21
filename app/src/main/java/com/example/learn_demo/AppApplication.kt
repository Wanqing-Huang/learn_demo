package com.example.learn_demo

import android.app.Application
import android.content.Context

/**
 * @author vianhuang
 * @date 2021/10/12 11:16 上午
 */
class AppApplication : Application() {
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        base?.let {
            AppUtils.initWith(it)
        }
    }
}
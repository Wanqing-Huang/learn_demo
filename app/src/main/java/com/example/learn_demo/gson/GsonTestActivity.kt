package com.example.learn_demo

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.GsonTestActivity.Companion.TAG
import com.example.learn_demo.gson.Account
import com.example.learn_demo.gson.AccountJsonDeserializer
import com.example.learn_demo.gson.AccountTypeAdapter
import com.google.gson.Gson
import com.google.gson.GsonBuilder

/**
 * @author vianhuang
 * @date 2021/7/9 5:10 下午
 * https://juejin.cn/post/6844903801783058440
 */
class GsonTestActivity : AppCompatActivity() {
    companion object {
        const val TAG = "GsonTestActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson)

        Thread {
            val gson = GsonBuilder().registerTypeAdapter(Account::class.java, AccountTypeAdapter())
                .create()
            testFromJson(gson, "AccountTypeAdapter")
            testToJson(gson, "AccountTypeAdapter")
        }.start()

        Thread {
            val gson = GsonBuilder().registerTypeAdapter(Account::class.java, AccountJsonDeserializer())
                .create()
            testFromJson(gson, "AccountJsonDeserializer")
            testToJson(gson, "AccountJsonDeserializer")
        }.start()

        Thread {
            val gson = Gson()
            testFromJson(gson, "ReflectiveTypeAdapter")
            testToJson(gson, "ReflectiveTypeAdapter")
        }.start()
    }
}


private fun testFromJson(gson: Gson, msg: String) {
    val start = System.currentTimeMillis()
    for (i in 1..10000) {
        val json = "{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}"
        gson.fromJson(json, Account::class.java)
    }
    Log.d(TAG, "[$msg] end fromJson test. cost=${System.currentTimeMillis() - start}")
}

private fun testToJson(gson: Gson, msg: String) {
    val account = Account().apply {
        uid = "123"
        telNumber = "000000000"
        userName = "test"
    }
    val start = System.currentTimeMillis()

    for (i in 1..10000) {
        gson.toJson(account)
    }
    Log.d(TAG, "[$msg] end toJson test. cost=${System.currentTimeMillis() - start}")
}

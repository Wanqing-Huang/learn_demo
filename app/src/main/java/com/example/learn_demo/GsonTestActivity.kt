package com.example.learn_demo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_gson.*

/**
 * @author vianhuang
 * @date 2021/7/9 5:10 下午
 * https://juejin.cn/post/6844903801783058440
 */
class GsonTestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gson)

        val json = "{\"uid\":\"00001\",\"userName\":\"Freeman\",\"telNumber\":\"13000000000\"}"
        val account = Gson().fromJson(json, Account::class.java)
        from_json.text = account.toString()
    }
}

data class Account(
    val uid: String,
    val userName: String,
    val telNumber: String
)
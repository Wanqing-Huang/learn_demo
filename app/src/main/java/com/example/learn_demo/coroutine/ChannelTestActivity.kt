package com.example.learn_demo.coroutine

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import kotlinx.android.synthetic.main.activity_channel_test.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlin.random.Random

/**
 * @author vianhuang
 * @date 2020/12/17 6:40 PM
 */
class ChannelTestActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "MainActivity"
        setContentView(R.layout.activity_channel_test)

        val channel = Channel<Int>(Channel.CONFLATED)

        btn_send.setOnClickListener {
            GlobalScope.launch {
                val value = Random.nextInt()
                Log.d("vian", "before send value. value = $value.")
                channel.send(value)
                Log.d("vian", "after send value. value = $value")
            }
        }

        btn_receive.setOnClickListener {
            GlobalScope.launch {
                Log.d("vian", "before receive value.")
                val value = channel.receive()
                Log.d("vian", "after receive value. value = $value")
            }
        }

        btn_offer.setOnClickListener {
            val value = Random.nextInt()
            Log.d("vian", "before offer value. value = $value")
            channel.offer(value)
            Log.d("vian", "after offer value. value = $value")
        }

        btn_poll.setOnClickListener {
            Log.d("vian", "before poll value.")
            val value = channel.poll()
            Log.d("vian", "after poll value. value = $value")
        }

    }
}
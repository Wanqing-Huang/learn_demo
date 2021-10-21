package com.example.learn_demo

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.aop.AOPActivity
import com.example.learn_demo.calendarview.CalendarViewActivity
import com.example.learn_demo.coroutine.ChannelTestActivity
import com.example.learn_demo.coroutine.CoroutineTestActivity
import com.example.learn_demo.db.DBTestActivity
import com.example.learn_demo.lock.LockTestActivity
import com.example.learn_demo.tasktest.TaskTestActivityA
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.io.FileOutputStream


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = "MainActivity"
        setContentView(R.layout.activity_main)

        btn_aop_test.setOnClickListener {
            startActivity(Intent(this, AOPActivity::class.java))
        }

        btn_date_picker_test.setOnClickListener {
            startActivity(Intent(this, CalendarViewActivity::class.java))
        }

        btn_android_q_test.setOnClickListener {
            val path = "/storage/emulated/0/foody/Picture/Picture_20201127180006.jpg"
            BitmapFactory.decodeFile("/storage/emulated/0/foody/Picture/Picture_20201127180006.jpg")

            val dirFile = File("/storage/emulated/0/foody/Picture")
            if (!dirFile.exists() || !dirFile.isDirectory) {
                if (!dirFile.mkdirs()) {
                    return@setOnClickListener
                }
            }

            FileOutputStream(path).let {
                it.write(1)
                it.flush()
                it.close()
            }
        }

        btn_activity_task_test.setOnClickListener {
            startActivityForResult(Intent(this, TaskTestActivityA::class.java), 1000)
        }


        btn_activity_channel_test.setOnClickListener {
            startActivity(Intent(this, ChannelTestActivity::class.java))
        }

        btn_activity_coroutine_test.setOnClickListener {
            startActivity(Intent(this, CoroutineTestActivity::class.java))
        }

        btn_lock_test.setOnClickListener {
            startActivity(Intent(this, LockTestActivity::class.java))
        }

        btn_flavor_test.setOnClickListener {
            startActivity(Intent(this, FlavorTestActivity::class.java))
        }

        btn_gson_test.setOnClickListener {
            startActivity(Intent(this, GsonTestActivity::class.java))
        }

        btn_db_test.setOnClickListener {
            startActivity(Intent(this, DBTestActivity::class.java))
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("vian", "MainActivity onActivityResult")
    }
}
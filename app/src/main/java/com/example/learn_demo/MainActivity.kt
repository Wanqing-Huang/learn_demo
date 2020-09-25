package com.example.learn_demo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.aop.AOPActivity
import com.example.learn_demo.calendarview.CalendarViewActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_aop_test.setOnClickListener {
            startActivity(Intent(this, AOPActivity::class.java))
        }

        btn_date_picker_test.setOnClickListener {
            startActivity(Intent(this, CalendarViewActivity::class.java))
        }
    }
}
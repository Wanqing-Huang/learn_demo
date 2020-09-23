package com.example.learn_demo.calendar

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import com.haibin.calendarview.Calendar
import com.haibin.calendarview.CalendarView
import kotlinx.android.synthetic.main.activity_simple_calendar.*

/**
 * @author vianhuang
 * @date 2020/9/23 4:18 PM
 */
class SimpleCalendarActivity: AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_simple_calendar)

        calendarView.setOnCalendarSelectListener(object : CalendarView.OnCalendarSelectListener {
            override fun onCalendarOutOfRange(calendar: Calendar?) {

            }

            override fun onCalendarSelect(calendar: Calendar?, isClick: Boolean) {
                calendar?.let {
                    tv_selected_date.text = "${it.day},${it.month},${it.year}"
                }
            }
        })

        tv_ok.setOnClickListener(this)
        tv_cancel.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.tv_ok -> {
                finish()
            }
            R.id.tv_cancel -> {
                finish()
            }
        }
    }
}
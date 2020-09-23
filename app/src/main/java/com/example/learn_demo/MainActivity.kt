package com.example.learn_demo

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.aop.AOPActivity
import com.example.learn_demo.calendar.CurrentDayDecorator
import com.example.learn_demo.calendar.SimpleCalendarActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.calendarView
import kotlinx.android.synthetic.main.activity_simple_calendar.*
import java.util.*


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calendarView.state().edit()
            .setMinimumDate(CalendarDay.from(2020, 3, 20))
            .setMaximumDate(CalendarDay.from(2020, 9, 23))
            .commit()
        val calendar = Calendar.getInstance()
        calendarView.selectedDate = CalendarDay.from(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        calendarView.addDecorator(
            CurrentDayDecorator(
                this,
                CalendarDay.from(
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH) + 1,
                    calendar.get(Calendar.DAY_OF_MONTH)
                )
            )
        )
//        calendarView.selectRange(CalendarDay.from(2020,3,20), CalendarDay.from(2020,9,23))

        btn_aop_test.setOnClickListener {
//            startActivity(Intent(this, AOPActivity::class.java))

            val calendar = Calendar.getInstance()
            DatePickerDialog(
                this,
                AlertDialog.THEME_DEVICE_DEFAULT_LIGHT,
                null,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            ).show()
        }

        btn_date_picker_test.setOnClickListener {
            startActivity(Intent(this, SimpleCalendarActivity::class.java))
        }


    }
}
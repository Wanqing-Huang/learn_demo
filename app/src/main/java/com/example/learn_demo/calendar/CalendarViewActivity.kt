package com.example.learn_demo.calendar

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class CalendarViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)

        calendarView.setTileHeightDp(50)
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
        calendarView.titleAnimationOrientation = MaterialCalendarView.HORIZONTAL
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
    }
}
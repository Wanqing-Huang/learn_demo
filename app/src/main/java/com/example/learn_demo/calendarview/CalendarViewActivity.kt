package com.example.learn_demo.calendarview

import android.content.Context
import android.os.Bundle
import android.text.format.DateUtils
import androidx.appcompat.app.AppCompatActivity
import com.example.learn_demo.R
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import kotlinx.android.synthetic.main.activity_calendar_view.*
import java.util.*

class CalendarViewActivity : AppCompatActivity() {
    private val mToday by lazy {
        val calendar = Calendar.getInstance()
        CalendarDay.from(
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH) + 1,
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    var selectedDate: CalendarDay = mToday

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar_view)

        //设置日期单元格大小
        calendar_view.setTileHeightDp(40)
        calendar_view.setTileWidthDp(42)

        //设置可选日期范围
        calendar_view.state().edit()
            .setMinimumDate(CalendarDay.from(2020, 3, 20))
            .setMaximumDate(mToday)
            .commit()

        //设置默认选中日期
        calendar_view.selectedDate = mToday
        tv_selected_date.text = formatDateString(this, mToday)

        //设置header动画类型
        calendar_view.titleAnimationOrientation = MaterialCalendarView.HORIZONTAL

        //设置当天样式
        calendar_view.addDecorator(
            CurrentDayDecorator(this, mToday)
        )

        //设置选中日期监听器
        calendar_view.setOnDateChangedListener { widget, date, selected ->
            if (selected) {
                tv_selected_date.text = formatDateString(this, date)
            }
        }

        tv_cancel.setOnClickListener {
            finish()
        }

        tv_ok.setOnClickListener {
            finish()
        }
    }

    private fun formatDateString(context: Context, date: CalendarDay): String {
        val formatFlags = DateUtils.FORMAT_NO_NOON_MIDNIGHT
            .or(DateUtils.FORMAT_ABBREV_ALL)
            .or(DateUtils.FORMAT_SHOW_DATE)
            .or(DateUtils.FORMAT_SHOW_YEAR)
        val calendar = Calendar.getInstance().apply {
            set(date.year, date.month, date.day)
        }
        return DateUtils.formatDateTime(context, calendar.timeInMillis, formatFlags)
    }
}